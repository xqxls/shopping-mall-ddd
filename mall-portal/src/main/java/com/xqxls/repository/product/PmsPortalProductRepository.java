package com.xqxls.repository.product;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.xqxls.convert.product.PmsPortalProductConvert;
import com.xqxls.convert.product.PortalBrandConvert;
import com.xqxls.dao.PortalProductDao;
import com.xqxls.domain.member.model.vo.SmsCouponVO;
import com.xqxls.domain.product.model.aggregates.PmsPortalProductDetailRich;
import com.xqxls.domain.product.model.res.PmsProductCategoryNodeResult;
import com.xqxls.domain.product.model.vo.*;
import com.xqxls.domain.product.repository.IPmsPortalProductRepository;
import com.xqxls.dto.PmsPortalProductDetail;
import com.xqxls.dto.PmsProductCategoryNode;
import com.xqxls.dto.PromotionProduct;
import com.xqxls.mapper.*;
import com.xqxls.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/2 20:21
 */
@Repository
public class PmsPortalProductRepository implements IPmsPortalProductRepository {

    @Resource
    private PmsProductMapper productMapper;
    @Resource
    private PmsProductCategoryMapper productCategoryMapper;
    @Resource
    private PmsBrandMapper brandMapper;
    @Resource
    private PmsProductAttributeMapper productAttributeMapper;
    @Resource
    private PmsProductAttributeValueMapper productAttributeValueMapper;
    @Resource
    private PmsSkuStockMapper skuStockMapper;
    @Resource
    private PmsProductLadderMapper productLadderMapper;
    @Resource
    private PmsProductFullReductionMapper productFullReductionMapper;
    @Resource
    private PortalProductDao portalProductDao;

    @Override
    public List<PmsProductVO> search(String keyword, Long brandId, Long productCategoryId, Integer pageNum, Integer pageSize, Integer sort) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(PmsProduct.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleteStatus",0);
        if (StrUtil.isNotEmpty(keyword)) {
            criteria.andLike("name","%" + keyword + "%");
        }
        if (brandId != null) {
            criteria.andEqualTo("brandId",brandId);
        }
        if (productCategoryId != null) {
            criteria.andEqualTo("productCategoryId",productCategoryId);
        }
        //1->按新品；2->按销量；3->价格从低到高；4->价格从高到低
        if (sort == 1) {
            example.setOrderByClause("id desc");
        } else if (sort == 2) {
            example.setOrderByClause("sale desc");
        } else if (sort == 3) {
            example.setOrderByClause("price asc");
        } else if (sort == 4) {
            example.setOrderByClause("price desc");
        }
        return PmsPortalProductConvert.INSTANCE.convertEntityToVOList(productMapper.selectByExample(example));
    }

    @Override
    public List<PmsProductCategoryNodeResult> categoryTreeList() {
        Example example = new Example(PmsProductCategory.class);
        List<PmsProductCategory> allList = productCategoryMapper.selectByExample(example);
        List<PmsProductCategoryNode> nodeList = allList.stream()
                .filter(item -> item.getParentId().equals(0L))
                .map(item -> covert(item, allList)).collect(Collectors.toList());
        List<PmsProductCategoryNodeResult> resultList = new ArrayList<>();
        for(PmsProductCategoryNode node:nodeList){
            PmsProductCategoryNodeResult result = new PmsProductCategoryNodeResult();
            BeanUtils.copyProperties(node,result);
            List<PmsProductCategoryNodeResult> childrenList = new ArrayList<>();
            for(PmsProductCategoryNode child:node.getChildren()){
                PmsProductCategoryNodeResult childResult = new PmsProductCategoryNodeResult();
                BeanUtils.copyProperties(child,childResult);
                childrenList.add(childResult);
            }
            result.setChildren(childrenList);
            resultList.add(result);
        }
        return resultList;
    }

    /**
     * 初始对象转化为节点对象
     */
    private PmsProductCategoryNode covert(PmsProductCategory item, List<PmsProductCategory> allList) {
        PmsProductCategoryNode node = new PmsProductCategoryNode();
        BeanUtils.copyProperties(item, node);
        List<PmsProductCategoryNode> children = allList.stream()
                .filter(subItem -> subItem.getParentId().equals(item.getId()))
                .map(subItem -> covert(subItem, allList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }

    @Override
    public PmsPortalProductDetailRich detail(Long id) {
        PmsPortalProductDetail result = new PmsPortalProductDetail();
        //获取商品信息
        PmsProduct product = productMapper.selectByPrimaryKey(id);
        result.setProduct(product);
        //获取品牌信息
        PmsBrand brand = brandMapper.selectByPrimaryKey(product.getBrandId());
        result.setBrand(brand);
        //获取商品属性信息
        Example attributeExample = new Example(PmsProductAttribute.class);
        attributeExample.createCriteria().andEqualTo("productAttributeCategoryId",product.getProductAttributeCategoryId());
        List<PmsProductAttribute> productAttributeList = productAttributeMapper.selectByExample(attributeExample);
        result.setProductAttributeList(productAttributeList);
        //获取商品属性值信息
        if(CollUtil.isNotEmpty(productAttributeList)){
            List<Long> attributeIds = productAttributeList.stream().map(PmsProductAttribute::getId).collect(Collectors.toList());
            Example attributeValueExample = new Example(PmsProductAttributeValue.class);
            attributeValueExample.createCriteria().andEqualTo("productId",product.getId())
                    .andIn("productAttributeId",attributeIds);
            List<PmsProductAttributeValue> productAttributeValueList = productAttributeValueMapper.selectByExample(attributeValueExample);
            result.setProductAttributeValueList(productAttributeValueList);
        }
        //获取商品SKU库存信息
        Example skuExample = new Example(PmsSkuStock.class);
        skuExample.createCriteria().andEqualTo("productId",product.getId());
        List<PmsSkuStock> skuStockList = skuStockMapper.selectByExample(skuExample);
        result.setSkuStockList(skuStockList);
        //商品阶梯价格设置
        if(product.getPromotionType()==3){
            Example ladderExample = new Example(PmsProductLadder.class);
            ladderExample.createCriteria().andEqualTo("productId",product.getId());
            List<PmsProductLadder> productLadderList = productLadderMapper.selectByExample(ladderExample);
            result.setProductLadderList(productLadderList);
        }
        //商品满减价格设置
        if(product.getPromotionType()==4){
            Example fullReductionExample = new Example(PmsProductFullReduction.class);
            fullReductionExample.createCriteria().andEqualTo("productId",product.getId());
            List<PmsProductFullReduction> productFullReductionList = productFullReductionMapper.selectByExample(fullReductionExample);
            result.setProductFullReductionList(productFullReductionList);
        }
        //商品可用优惠券
        result.setCouponList(portalProductDao.getAvailableCouponList(product.getId(),product.getProductCategoryId()));
        return convertResultToRich(result);
    }

    @Override
    public List<PromotionProduct> getPromotionProductList(List<Long> productIdList) {

        return portalProductDao.getPromotionProductList(productIdList);
    }

    private PmsPortalProductDetailRich convertResultToRich(PmsPortalProductDetail result) {
        PmsPortalProductDetailRich rich = new PmsPortalProductDetailRich();
        PmsProductVO productVO = PmsPortalProductConvert.INSTANCE.convertEntityToVO(result.getProduct());
        PmsBrandVO brandVO = PortalBrandConvert.INSTANCE.convertEntityToVO(result.getBrand());
        List<PmsProductAttributeVO> productAttributeVOList =PmsPortalProductConvert.INSTANCE.pmsProductAttributeEntityToVOList(result.getProductAttributeList());
        List<PmsProductAttributeValueVO> productAttributeValueVOList =PmsPortalProductConvert.INSTANCE.pmsProductAttributeValueEntityToVOList(result.getProductAttributeValueList());
        List<PmsSkuStockVO> skuStockVOList = PmsPortalProductConvert.INSTANCE.pmsSkuStockEntityToVOList(result.getSkuStockList());
        List<PmsProductLadderVO> productLadderVOList = PmsPortalProductConvert.INSTANCE.pmsProductLadderEntityToVOList(result.getProductLadderList());
        List<PmsProductFullReductionVO> productFullReductionVOList = PmsPortalProductConvert.INSTANCE.pmsProductFullReductionEntityToVOList(result.getProductFullReductionList());
        List<SmsCouponVO> couponVOList = PmsPortalProductConvert.INSTANCE.smsCouponEntityToVOList(result.getCouponList());
        rich.setProduct(productVO);
        rich.setBrand(brandVO);
        rich.setProductAttributeList(productAttributeVOList);
        rich.setProductAttributeValueList(productAttributeValueVOList);
        rich.setSkuStockList(skuStockVOList);
        rich.setProductLadderList(productLadderVOList);
        rich.setProductFullReductionList(productFullReductionVOList);
        rich.setCouponList(couponVOList);
        return rich;
    }
}

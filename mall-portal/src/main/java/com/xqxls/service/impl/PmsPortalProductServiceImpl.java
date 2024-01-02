package com.xqxls.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.xqxls.mapper.*;
import com.xqxls.model.*;
import com.xqxls.dao.PortalProductDao;
import com.xqxls.domain.PmsPortalProductDetail;
import com.xqxls.domain.PmsProductCategoryNode;
import com.xqxls.service.PmsPortalProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 前台订单管理Service实现类
 * Created by macro on 2020/4/6.
 */
@Service
public class PmsPortalProductServiceImpl implements PmsPortalProductService {
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
    public List<PmsProduct> search(String keyword, Long brandId, Long productCategoryId, Integer pageNum, Integer pageSize, Integer sort) {
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
        return productMapper.selectByExample(example);
    }

    @Override
    public List<PmsProductCategoryNode> categoryTreeList() {
        Example example = new Example(PmsProductCategory.class);
        List<PmsProductCategory> allList = productCategoryMapper.selectByExample(example);
        List<PmsProductCategoryNode> result = allList.stream()
                .filter(item -> item.getParentId().equals(0L))
                .map(item -> covert(item, allList)).collect(Collectors.toList());
        return result;
    }

    @Override
    public PmsPortalProductDetail detail(Long id) {
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
        return result;
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
}

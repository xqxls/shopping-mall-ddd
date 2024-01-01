package com.xqxls.repository.pms;

import cn.hutool.core.collection.CollUtil;
import com.github.pagehelper.PageHelper;
import com.xqxls.convert.pms.*;
import com.xqxls.dao.*;
import com.xqxls.mapper.*;
import com.xqxls.model.*;
import com.xqxls.pms.model.aggregates.PmsProductRich;
import com.xqxls.pms.model.req.PmsProductReq;
import com.xqxls.pms.model.res.PmsProductUpdateResult;
import com.xqxls.pms.model.vo.PmsProductVO;
import com.xqxls.pms.repository.IPmsProductRepository;
import com.xqxls.pms.service.impl.PmsProductServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/28 16:34
 */
@Repository
public class PmsProductRepository implements IPmsProductRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsProductServiceImpl.class);

    @Resource
    private PmsProductMapper productMapper;
    @Resource
    private PmsMemberPriceDao memberPriceDao;
    @Resource
    private PmsMemberPriceMapper memberPriceMapper;
    @Resource
    private PmsProductLadderDao productLadderDao;
    @Resource
    private PmsProductLadderMapper productLadderMapper;
    @Resource
    private PmsProductFullReductionDao productFullReductionDao;
    @Resource
    private PmsProductFullReductionMapper productFullReductionMapper;
    @Resource
    private PmsSkuStockDao skuStockDao;
    @Resource
    private PmsSkuStockMapper skuStockMapper;
    @Resource
    private PmsProductAttributeValueDao productAttributeValueDao;
    @Resource
    private PmsProductAttributeValueMapper productAttributeValueMapper;
    @Resource
    private CmsSubjectProductRelationDao subjectProductRelationDao;
    @Resource
    private CmsSubjectProductRelationMapper subjectProductRelationMapper;
    @Resource
    private CmsPrefrenceAreaProductRelationDao prefrenceAreaProductRelationDao;
    @Resource
    private CmsPrefrenceAreaProductRelationMapper prefrenceAreaProductRelationMapper;
    @Resource
    private PmsProductDao productDao;
    @Resource
    private PmsProductVertifyRecordDao productVertifyRecordDao;

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public int create(PmsProductRich pmsProductRich) {
        int count;
        //创建商品
        PmsProductVO productVO = pmsProductRich.getPmsProductVO();
        PmsProduct product = PmsProductConvert.INSTANCE.pmsProductVOToEntity(productVO);
        product.setId(null);
        productMapper.insertSelective(product);
        //根据促销类型设置价格：会员价格、阶梯价格、满减价格
        Long productId = product.getId();
        //会员价格
        relateAndInsertList(memberPriceDao, PmsMemberPriceConvert.INSTANCE.pmsMemberPriceVOToEntityList(pmsProductRich.getMemberPriceList()), productId);
        //阶梯价格
        relateAndInsertList(productLadderDao, PmsProductLadderConvert.INSTANCE.pmsProductLadderVOToEntityList(pmsProductRich.getProductLadderList()), productId);
        //满减价格
        relateAndInsertList(productFullReductionDao, PmsProductFullReductionConvert.INSTANCE.pmsProductFullReductionVOToEntityList(pmsProductRich.getProductFullReductionList()), productId);
        //处理sku的编码
        handleSkuStockCode(PmsSkuStockConvert.INSTANCE.pmsSkuStockVOToEntityList(pmsProductRich.getSkuStockList()),productId);
        //添加sku库存信息
        relateAndInsertList(skuStockDao, PmsSkuStockConvert.INSTANCE.pmsSkuStockVOToEntityList(pmsProductRich.getSkuStockList()), productId);
        //添加商品参数,添加自定义商品规格
        relateAndInsertList(productAttributeValueDao, PmsProductAttributeValueConvert.INSTANCE.pmsProductAttributeValueVOToEntityList(pmsProductRich.getProductAttributeValueList()), productId);
        //关联专题
        relateAndInsertList(subjectProductRelationDao, CmsSubjectProductRelationConvert.INSTANCE.cmsSubjectProductRelationVOToEntityList(pmsProductRich.getSubjectProductRelationList()), productId);
        //关联优选
        relateAndInsertList(prefrenceAreaProductRelationDao, CmsPrefrenceAreaProductRelationConvert.INSTANCE.cmsPrefrenceAreaProductRelationVOToEntityList(pmsProductRich.getPrefrenceAreaProductRelationList()), productId);
        count = 1;
        return count;
    }

    @Override
    public PmsProductUpdateResult getUpdateInfo(Long id) {

        return PmsProductConvert.INSTANCE.pmsProductResultToUpdateResult(productDao.getUpdateInfo(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(Long id, PmsProductRich pmsProductRich) {
        int count;
        //更新商品信息
        PmsProductVO productVO = pmsProductRich.getPmsProductVO();
        PmsProduct product = PmsProductConvert.INSTANCE.pmsProductVOToEntity(productVO);
        product.setId(id);
        productMapper.updateByPrimaryKeySelective(product);
        //会员价格
        Example pmsMemberPriceExample = new Example(PmsMemberPrice.class);
        pmsMemberPriceExample.createCriteria().andEqualTo("productId",id);
        memberPriceMapper.deleteByExample(pmsMemberPriceExample);
        relateAndInsertList(memberPriceDao, PmsMemberPriceConvert.INSTANCE.pmsMemberPriceVOToEntityList(pmsProductRich.getMemberPriceList()), id);
        //阶梯价格
        Example ladderExample = new Example(PmsProductLadder.class);
        ladderExample.createCriteria().andEqualTo("productId",id);
        productLadderMapper.deleteByExample(ladderExample);
        relateAndInsertList(productLadderDao, PmsProductLadderConvert.INSTANCE.pmsProductLadderVOToEntityList(pmsProductRich.getProductLadderList()), id);
        //满减价格
        Example fullReductionExample = new Example(PmsProductFullReduction.class);
        fullReductionExample.createCriteria().andEqualTo("productId",id);
        productFullReductionMapper.deleteByExample(fullReductionExample);
        relateAndInsertList(productFullReductionDao, PmsProductFullReductionConvert.INSTANCE.pmsProductFullReductionVOToEntityList(pmsProductRich.getProductFullReductionList()), id);
        //修改sku库存信息
        handleUpdateSkuStockList(id, PmsSkuStockConvert.INSTANCE.pmsSkuStockVOToEntityList(pmsProductRich.getSkuStockList()));
        //修改商品参数,添加自定义商品规格
        Example productAttributeValueExample = new Example(PmsProductAttributeValue.class);
        productAttributeValueExample.createCriteria().andEqualTo("productId",id);
        productAttributeValueMapper.deleteByExample(productAttributeValueExample);
        relateAndInsertList(productAttributeValueDao, PmsProductAttributeValueConvert.INSTANCE.pmsProductAttributeValueVOToEntityList(pmsProductRich.getProductAttributeValueList()), id);
        //关联专题
        Example subjectProductRelationExample = new Example(CmsSubjectProductRelation.class);
        subjectProductRelationExample.createCriteria().andEqualTo("productId",id);
        subjectProductRelationMapper.deleteByExample(subjectProductRelationExample);
        relateAndInsertList(subjectProductRelationDao, CmsSubjectProductRelationConvert.INSTANCE.cmsSubjectProductRelationVOToEntityList(pmsProductRich.getSubjectProductRelationList()), id);
        //关联优选
        Example prefrenceAreaExample = new Example(CmsPrefrenceAreaProductRelation.class);
        prefrenceAreaExample.createCriteria().andEqualTo("productId",id);
        prefrenceAreaProductRelationMapper.deleteByExample(prefrenceAreaExample);
        relateAndInsertList(prefrenceAreaProductRelationDao, CmsPrefrenceAreaProductRelationConvert.INSTANCE.cmsPrefrenceAreaProductRelationVOToEntityList(pmsProductRich.getPrefrenceAreaProductRelationList()), id);
        count = 1;
        return count;
    }

    private void handleUpdateSkuStockList(Long id, List<PmsSkuStock> currSkuList) {
        //当前没有sku直接删除
        if(CollUtil.isEmpty(currSkuList)){
            Example skuStockExample = new Example(PmsSkuStock.class);
            skuStockExample.createCriteria().andEqualTo("productId",id);
            skuStockMapper.deleteByExample(skuStockExample);
            return;
        }
        //获取初始sku信息
        Example skuStockExample = new Example(PmsSkuStock.class);
        skuStockExample.createCriteria().andEqualTo("productId",id);
        List<PmsSkuStock> oriStuList = skuStockMapper.selectByExample(skuStockExample);
        //获取新增sku信息
        List<PmsSkuStock> insertSkuList = currSkuList.stream().filter(item->item.getId()==null).collect(Collectors.toList());
        //获取需要更新的sku信息
        List<PmsSkuStock> updateSkuList = currSkuList.stream().filter(item->item.getId()!=null).collect(Collectors.toList());
        List<Long> updateSkuIds = updateSkuList.stream().map(PmsSkuStock::getId).collect(Collectors.toList());
        //获取需要删除的sku信息
        List<PmsSkuStock> removeSkuList = oriStuList.stream().filter(item-> !updateSkuIds.contains(item.getId())).collect(Collectors.toList());
        handleSkuStockCode(insertSkuList,id);
        handleSkuStockCode(updateSkuList,id);
        //新增sku
        if(CollUtil.isNotEmpty(insertSkuList)){
            relateAndInsertList(skuStockDao, insertSkuList, id);
        }
        //删除sku
        if(CollUtil.isNotEmpty(removeSkuList)){
            List<Long> removeSkuIds = removeSkuList.stream().map(PmsSkuStock::getId).collect(Collectors.toList());
            Example removeExample = new Example(PmsSkuStock.class);
            removeExample.createCriteria().andIn("id",removeSkuIds);
            skuStockMapper.deleteByExample(removeExample);
        }
        //修改sku
        if(CollUtil.isNotEmpty(updateSkuList)){
            for (PmsSkuStock pmsSkuStock : updateSkuList) {
                skuStockMapper.updateByPrimaryKeySelective(pmsSkuStock);
            }
        }

    }

    @Override
    public List<PmsProductVO> list(PmsProductReq pmsProductReq, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        Example productExample = new Example(PmsProduct.class);
        Example.Criteria criteria = productExample.createCriteria();
        criteria.andEqualTo("deleteStatus",0);
        if (pmsProductReq.getPublishStatus() != null) {
            criteria.andEqualTo("publishStatus",pmsProductReq.getPublishStatus());
        }
        if (pmsProductReq.getVerifyStatus() != null) {
            criteria.andEqualTo("verifyStatus",pmsProductReq.getVerifyStatus());
        }
        if (StringUtils.hasText(pmsProductReq.getKeyword())) {
            criteria.andLike("name","%" + pmsProductReq.getKeyword() + "%");
        }
        if (StringUtils.hasText(pmsProductReq.getProductSn())) {
            criteria.andEqualTo("productSn",pmsProductReq.getProductSn());
        }
        if (pmsProductReq.getBrandId() != null) {
            criteria.andEqualTo("brandId",pmsProductReq.getBrandId());
        }
        if (pmsProductReq.getProductCategoryId() != null) {
            criteria.andEqualTo("productCategoryId",pmsProductReq.getProductCategoryId());
        }
        return PmsProductConvert.INSTANCE.pmsProductEntityToVOList(productMapper.selectByExample(productExample));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateVerifyStatus(List<Long> ids, Integer verifyStatus, String detail) {
        PmsProduct product = new PmsProduct();
        product.setVerifyStatus(verifyStatus);
        Example example = new Example(PmsProduct.class);
        example.createCriteria().andIn("id",ids);
        List<PmsProductVertifyRecord> list = new ArrayList<>();
        int count = productMapper.updateByExampleSelective(product, example);
        //修改完审核状态后插入审核记录
        for (Long id : ids) {
            PmsProductVertifyRecord record = new PmsProductVertifyRecord();
            record.setProductId(id);
            record.setCreateTime(new Date());
            record.setDetail(detail);
            record.setStatus(verifyStatus);
            record.setVertifyMan("test");
            list.add(record);
        }
        productVertifyRecordDao.insertList(list);
        return count;
    }

    @Override
    public int updatePublishStatus(List<Long> ids, Integer publishStatus) {
        PmsProduct record = new PmsProduct();
        record.setPublishStatus(publishStatus);
        Example example = new Example(PmsProduct.class);
        example.createCriteria().andIn("id",ids);
        return productMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        PmsProduct record = new PmsProduct();
        record.setRecommandStatus(recommendStatus);
        Example example = new Example(PmsProduct.class);
        example.createCriteria().andIn("id",ids);
        return productMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateNewStatus(List<Long> ids, Integer newStatus) {
        PmsProduct record = new PmsProduct();
        record.setNewStatus(newStatus);
        Example example = new Example(PmsProduct.class);
        example.createCriteria().andIn("id",ids);
        return productMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateDeleteStatus(List<Long> ids, Integer deleteStatus) {
        PmsProduct record = new PmsProduct();
        record.setDeleteStatus(deleteStatus);
        Example example = new Example(PmsProduct.class);
        example.createCriteria().andIn("id",ids);
        return productMapper.updateByExampleSelective(record, example);
    }

    @Override
    public List<PmsProductVO> list(String keyword) {
        Example example = new Example(PmsProduct.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleteStatus",0);
        if(StringUtils.hasText(keyword)){
            criteria.andLike("name","%" + keyword + "%");
            example.or().andEqualTo("deleteStatus",0).andLike("productSn","%" + keyword + "%");
        }
        return PmsProductConvert.INSTANCE.pmsProductEntityToVOList(productMapper.selectByExample(example));
    }

    /**
     * 建立和插入关系表操作
     *
     * @param dao       可以操作的dao
     * @param dataList  要插入的数据
     * @param productId 建立关系的id
     */
    private void relateAndInsertList(Object dao, List dataList, Long productId) {
        try {
            if (CollectionUtils.isEmpty(dataList)) return;
            for (Object item : dataList) {
                Method setId = item.getClass().getMethod("setId", Long.class);
                setId.invoke(item, (Long) null);
                Method setProductId = item.getClass().getMethod("setProductId", Long.class);
                setProductId.invoke(item, productId);
            }
            Method insertList = dao.getClass().getMethod("insertList", List.class);
            insertList.invoke(dao, dataList);
        } catch (Exception e) {
            LOGGER.warn("创建产品出错:{}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    private void handleSkuStockCode(List<PmsSkuStock> skuStockList, Long productId) {
        if(CollectionUtils.isEmpty(skuStockList))return;
        for(int i=0;i<skuStockList.size();i++){
            PmsSkuStock skuStock = skuStockList.get(i);
            if(!StringUtils.hasText(skuStock.getSkuCode())){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                StringBuilder sb = new StringBuilder();
                //日期
                sb.append(sdf.format(new Date()));
                //四位商品id
                sb.append(String.format("%04d", productId));
                //三位索引id
                sb.append(String.format("%03d", i+1));
                skuStock.setSkuCode(sb.toString());
            }
        }
    }
}

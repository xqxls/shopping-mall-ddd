package com.xqxls.repository.pms;

import com.github.pagehelper.PageHelper;
import com.xqxls.convert.pms.PmsProductCategoryConvert;
import com.xqxls.dao.PmsProductCategoryAttributeRelationDao;
import com.xqxls.dao.PmsProductCategoryDao;
import com.xqxls.dto.PmsProductCategoryWithChildrenItem;
import com.xqxls.mapper.PmsProductCategoryAttributeRelationMapper;
import com.xqxls.mapper.PmsProductCategoryMapper;
import com.xqxls.mapper.PmsProductMapper;
import com.xqxls.model.*;
import com.xqxls.pms.model.req.PmsProductCategoryReq;
import com.xqxls.pms.model.res.PmsProductCategoryWithChildrenItemResult;
import com.xqxls.pms.model.vo.PmsProductCategoryVO;
import com.xqxls.pms.repository.IPmsProductCategoryRepository;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/28 14:44
 */
@Repository
public class PmsProductCategoryRepository implements IPmsProductCategoryRepository {

    @Resource
    private PmsProductCategoryMapper productCategoryMapper;
    @Resource
    private PmsProductCategoryDao productCategoryDao;
    @Resource
    private PmsProductMapper productMapper;
    @Resource
    private PmsProductCategoryAttributeRelationDao productCategoryAttributeRelationDao;
    @Resource
    private PmsProductCategoryAttributeRelationMapper productCategoryAttributeRelationMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int create(PmsProductCategoryReq pmsProductCategoryReq) {
        PmsProductCategory productCategory = new PmsProductCategory();
        productCategory.setProductCount(0);
        BeanUtils.copyProperties(pmsProductCategoryReq, productCategory);
        //没有父分类时为一级分类
        setCategoryLevel(productCategory);
        int count = productCategoryMapper.insertSelective(productCategory);
        //创建筛选属性关联
        List<Long> productAttributeIdList = pmsProductCategoryReq.getProductAttributeIdList();
        if(!CollectionUtils.isEmpty(productAttributeIdList)){
            insertRelationList(productCategory.getId(), productAttributeIdList);
        }
        return count;
    }

    /**
     * 根据分类的parentId设置分类的level
     */
    private void setCategoryLevel(PmsProductCategory productCategory) {
        //没有父分类时为一级分类
        if (productCategory.getParentId() == 0) {
            productCategory.setLevel(0);
        } else {
            //有父分类时选择根据父分类level设置
            PmsProductCategory parentCategory = productCategoryMapper.selectByPrimaryKey(productCategory.getParentId());
            if (parentCategory != null) {
                productCategory.setLevel(parentCategory.getLevel() + 1);
            } else {
                productCategory.setLevel(0);
            }
        }
    }

    /**
     * 批量插入商品分类与筛选属性关系表
     * @param productCategoryId 商品分类id
     * @param productAttributeIdList 相关商品筛选属性id集合
     */
    private void insertRelationList(Long productCategoryId, List<Long> productAttributeIdList) {
        List<PmsProductCategoryAttributeRelation> relationList = new ArrayList<>();
        for (Long productAttrId : productAttributeIdList) {
            PmsProductCategoryAttributeRelation relation = new PmsProductCategoryAttributeRelation();
            relation.setProductAttributeId(productAttrId);
            relation.setProductCategoryId(productCategoryId);
            relationList.add(relation);
        }
        productCategoryAttributeRelationDao.insertList(relationList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(Long id, PmsProductCategoryReq pmsProductCategoryReq) {
        PmsProductCategory productCategory = new PmsProductCategory();
        productCategory.setId(id);
        BeanUtils.copyProperties(pmsProductCategoryReq, productCategory);
        setCategoryLevel(productCategory);
        //更新商品分类时要更新商品中的名称
        PmsProduct product = new PmsProduct();
        product.setProductCategoryName(productCategory.getName());
        PmsProductExample example = new PmsProductExample();
        example.createCriteria().andProductCategoryIdEqualTo(id);
        productMapper.updateByExampleSelective(product,example);
        //同时更新筛选属性的信息
        if(!CollectionUtils.isEmpty(pmsProductCategoryReq.getProductAttributeIdList())){
            PmsProductCategoryAttributeRelationExample relationExample = new PmsProductCategoryAttributeRelationExample();
            relationExample.createCriteria().andProductCategoryIdEqualTo(id);
            productCategoryAttributeRelationMapper.deleteByExample(relationExample);
            insertRelationList(id,pmsProductCategoryReq.getProductAttributeIdList());
        }else{
            PmsProductCategoryAttributeRelationExample relationExample = new PmsProductCategoryAttributeRelationExample();
            relationExample.createCriteria().andProductCategoryIdEqualTo(id);
            productCategoryAttributeRelationMapper.deleteByExample(relationExample);
        }
        return productCategoryMapper.updateByPrimaryKeySelective(productCategory);
    }

    @Override
    public List<PmsProductCategoryVO> getList(Long parentId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        PmsProductCategoryExample example = new PmsProductCategoryExample();
        example.setOrderByClause("sort desc");
        example.createCriteria().andParentIdEqualTo(parentId);
        return PmsProductCategoryConvert.INSTANCE.pmsProductCategoryEntityToVOList(productCategoryMapper.selectByExample(example));
    }

    @Override
    public int delete(Long id) {
        return productCategoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PmsProductCategoryVO getItem(Long id) {
        return PmsProductCategoryConvert.INSTANCE.pmsProductCategoryEntityToVO(productCategoryMapper.selectByPrimaryKey(id));
    }

    @Override
    public int updateNavStatus(List<Long> ids, Integer navStatus) {
        PmsProductCategory productCategory = new PmsProductCategory();
        productCategory.setNavStatus(navStatus);
        PmsProductCategoryExample example = new PmsProductCategoryExample();
        example.createCriteria().andIdIn(ids);
        return productCategoryMapper.updateByExampleSelective(productCategory, example);
    }

    @Override
    public int updateShowStatus(List<Long> ids, Integer showStatus) {
        PmsProductCategory productCategory = new PmsProductCategory();
        productCategory.setShowStatus(showStatus);
        PmsProductCategoryExample example = new PmsProductCategoryExample();
        example.createCriteria().andIdIn(ids);
        return productCategoryMapper.updateByExampleSelective(productCategory, example);
    }

    @Override
    public List<PmsProductCategoryWithChildrenItemResult> listWithChildren() {
        List<PmsProductCategoryWithChildrenItem> pmsProductCategoryWithChildrenItemList = productCategoryDao.listWithChildren();
        List<PmsProductCategoryWithChildrenItemResult> itemResultList = new ArrayList<>();
        for(PmsProductCategoryWithChildrenItem item:pmsProductCategoryWithChildrenItemList){
            PmsProductCategoryWithChildrenItemResult itemResult = new PmsProductCategoryWithChildrenItemResult();
            BeanUtils.copyProperties(item,itemResult);
            List<PmsProductCategoryVO> productCategoryVOList = new ArrayList<>();
            for(PmsProductCategory pmsProductCategory:item.getChildren()){
                PmsProductCategoryVO pmsProductCategoryVO = new PmsProductCategoryVO();
                BeanUtils.copyProperties(pmsProductCategory,pmsProductCategoryVO);
                productCategoryVOList.add(pmsProductCategoryVO);
            }
            itemResult.setChildren(productCategoryVOList);
            itemResultList.add(itemResult);
        }
        return itemResultList;
    }
}

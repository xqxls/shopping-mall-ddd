package com.xqxls.repository.pms;

import com.github.pagehelper.PageHelper;
import com.xqxls.convert.pms.PmsProductAttributeConvert;
import com.xqxls.dao.PmsProductAttributeDao;
import com.xqxls.mapper.PmsProductAttributeCategoryMapper;
import com.xqxls.mapper.PmsProductAttributeMapper;
import com.xqxls.model.PmsProductAttribute;
import com.xqxls.model.PmsProductAttributeCategory;
import com.xqxls.model.PmsProductAttributeExample;
import com.xqxls.pms.model.req.PmsProductAttributeReq;
import com.xqxls.pms.model.res.ProductAttrInfoResult;
import com.xqxls.pms.model.vo.PmsProductAttributeVO;
import com.xqxls.pms.repository.IPmsProductAttributeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/28 11:32
 */
@Repository
public class PmsProductAttributeRepository implements IPmsProductAttributeRepository {

    @Resource
    private PmsProductAttributeMapper productAttributeMapper;
    @Resource
    private PmsProductAttributeCategoryMapper productAttributeCategoryMapper;
    @Resource
    private PmsProductAttributeDao productAttributeDao;

    @Override
    public List<PmsProductAttributeVO> getList(Long cid, Integer type, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        PmsProductAttributeExample example = new PmsProductAttributeExample();
        example.setOrderByClause("sort desc");
        example.createCriteria().andProductAttributeCategoryIdEqualTo(cid).andTypeEqualTo(type);
        return PmsProductAttributeConvert.INSTANCE.pmsProductAttributeEntityToVOList(productAttributeMapper.selectByExample(example));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int create(PmsProductAttributeReq pmsProductAttributeReq) {
        PmsProductAttribute pmsProductAttribute = new PmsProductAttribute();
        BeanUtils.copyProperties(pmsProductAttributeReq, pmsProductAttribute);
        int count = productAttributeMapper.insertSelective(pmsProductAttribute);
        //新增商品属性以后需要更新商品属性分类数量
        PmsProductAttributeCategory pmsProductAttributeCategory = productAttributeCategoryMapper.selectByPrimaryKey(pmsProductAttribute.getProductAttributeCategoryId());
        if(pmsProductAttribute.getType()==0){
            pmsProductAttributeCategory.setAttributeCount(pmsProductAttributeCategory.getAttributeCount()+1);
        }else if(pmsProductAttribute.getType()==1){
            pmsProductAttributeCategory.setParamCount(pmsProductAttributeCategory.getParamCount()+1);
        }
        productAttributeCategoryMapper.updateByPrimaryKey(pmsProductAttributeCategory);
        return count;
    }

    @Override
    public int update(Long id, PmsProductAttributeReq pmsProductAttributeReq) {
        PmsProductAttribute pmsProductAttribute = new PmsProductAttribute();
        pmsProductAttribute.setId(id);
        BeanUtils.copyProperties(pmsProductAttributeReq, pmsProductAttribute);
        return productAttributeMapper.updateByPrimaryKeySelective(pmsProductAttribute);
    }

    @Override
    public PmsProductAttributeVO getItem(Long id) {
        return PmsProductAttributeConvert.INSTANCE.pmsProductAttributeEntityToVO(productAttributeMapper.selectByPrimaryKey(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(List<Long> ids) {
        //获取分类
        PmsProductAttribute pmsProductAttribute = productAttributeMapper.selectByPrimaryKey(ids.get(0));
        Integer type = pmsProductAttribute.getType();
        PmsProductAttributeCategory pmsProductAttributeCategory = productAttributeCategoryMapper.selectByPrimaryKey(pmsProductAttribute.getProductAttributeCategoryId());
        PmsProductAttributeExample example = new PmsProductAttributeExample();
        example.createCriteria().andIdIn(ids);
        int count = productAttributeMapper.deleteByExample(example);
        //删除完成后修改数量
        if(type==0){
            if(pmsProductAttributeCategory.getAttributeCount()>=count){
                pmsProductAttributeCategory.setAttributeCount(pmsProductAttributeCategory.getAttributeCount()-count);
            }else{
                pmsProductAttributeCategory.setAttributeCount(0);
            }
        }else if(type==1){
            if(pmsProductAttributeCategory.getParamCount()>=count){
                pmsProductAttributeCategory.setParamCount(pmsProductAttributeCategory.getParamCount()-count);
            }else{
                pmsProductAttributeCategory.setParamCount(0);
            }
        }
        productAttributeCategoryMapper.updateByPrimaryKey(pmsProductAttributeCategory);
        return count;
    }

    @Override
    public List<ProductAttrInfoResult> getProductAttrInfo(Long productCategoryId) {
        return PmsProductAttributeConvert.INSTANCE.pmsProductAttributeInfoToResultList(productAttributeDao.getProductAttrInfo(productCategoryId));
    }
}

package com.xqxls.repository.pms;

import com.github.pagehelper.PageHelper;
import com.xqxls.convert.pms.PmsProductAttributeCategoryConvert;
import com.xqxls.dao.PmsProductAttributeCategoryDao;
import com.xqxls.dto.PmsProductAttributeCategoryItem;
import com.xqxls.mapper.PmsProductAttributeCategoryMapper;
import com.xqxls.model.PmsProductAttribute;
import com.xqxls.model.PmsProductAttributeCategory;
import com.xqxls.model.PmsProductAttributeCategoryExample;
import com.xqxls.pms.model.res.PmsProductAttributeCategoryItemResult;
import com.xqxls.pms.model.vo.PmsProductAttributeCategoryVO;
import com.xqxls.pms.model.vo.PmsProductAttributeVO;
import com.xqxls.pms.repository.IPmsProductAttributeCategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/28 11:02
 */
@Repository
public class PmsProductAttributeCategoryRepository implements IPmsProductAttributeCategoryRepository {

    @Resource
    private PmsProductAttributeCategoryMapper productAttributeCategoryMapper;
    @Resource
    private PmsProductAttributeCategoryDao productAttributeCategoryDao;

    @Override
    public int create(String name) {
        PmsProductAttributeCategory productAttributeCategory = new PmsProductAttributeCategory();
        productAttributeCategory.setName(name);
        return productAttributeCategoryMapper.insertSelective(productAttributeCategory);
    }

    @Override
    public int update(Long id, String name) {
        PmsProductAttributeCategory productAttributeCategory = new PmsProductAttributeCategory();
        productAttributeCategory.setName(name);
        productAttributeCategory.setId(id);
        return productAttributeCategoryMapper.updateByPrimaryKeySelective(productAttributeCategory);
    }

    @Override
    public int delete(Long id) {
        return productAttributeCategoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PmsProductAttributeCategoryVO getItem(Long id) {
        return PmsProductAttributeCategoryConvert.INSTANCE.pmsProductAttributeCategoryEntityToVO(productAttributeCategoryMapper.selectByPrimaryKey(id));
    }

    @Override
    public List<PmsProductAttributeCategoryVO> getList(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        return PmsProductAttributeCategoryConvert.INSTANCE.pmsProductAttributeCategoryEntityToVOList(productAttributeCategoryMapper.selectByExample(new PmsProductAttributeCategoryExample()));
    }

    @Override
    public List<PmsProductAttributeCategoryItemResult> getListWithAttr() {
        List<PmsProductAttributeCategoryItem> pmsProductAttributeCategoryItemList = productAttributeCategoryDao.getListWithAttr();
        List<PmsProductAttributeCategoryItemResult> itemResultList = new ArrayList<>();
        for(PmsProductAttributeCategoryItem item:pmsProductAttributeCategoryItemList){
            PmsProductAttributeCategoryItemResult itemResult = new PmsProductAttributeCategoryItemResult();
            BeanUtils.copyProperties(item,itemResult);
            List<PmsProductAttributeVO> productAttributeVOList = new ArrayList<>();
            for(PmsProductAttribute pmsProductAttribute:item.getProductAttributeList()){
                PmsProductAttributeVO pmsProductAttributeVO = new PmsProductAttributeVO();
                BeanUtils.copyProperties(pmsProductAttribute,pmsProductAttributeVO);
                productAttributeVOList.add(pmsProductAttributeVO);
            }
            itemResult.setProductAttributeList(productAttributeVOList);
            itemResultList.add(itemResult);
        }
        return itemResultList;
    }
}

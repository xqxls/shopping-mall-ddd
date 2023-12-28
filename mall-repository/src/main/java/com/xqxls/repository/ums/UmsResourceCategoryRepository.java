package com.xqxls.repository.ums;

import com.xqxls.convert.ums.UmsResourceCategoryConvert;
import com.xqxls.mapper.UmsResourceCategoryMapper;
import com.xqxls.model.UmsResourceCategory;
import com.xqxls.model.UmsResourceCategoryExample;
import com.xqxls.ums.model.vo.UmsResourceCategoryVO;
import com.xqxls.ums.repository.IUmsResourceCategoryRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/27 21:48
 */
@Repository
public class UmsResourceCategoryRepository implements IUmsResourceCategoryRepository {

    @Resource
    private UmsResourceCategoryMapper resourceCategoryMapper;

    @Override
    public List<UmsResourceCategoryVO> listAll() {
        UmsResourceCategoryExample example = new UmsResourceCategoryExample();
        example.setOrderByClause("sort desc");
        return UmsResourceCategoryConvert.INSTANCE.convertResourceCategoryList(resourceCategoryMapper.selectByExample(example));
    }

    @Override
    public int create(UmsResourceCategoryVO umsResourceCategoryVO) {
        UmsResourceCategory umsResourceCategory = UmsResourceCategoryConvert.INSTANCE.umsResourceCategoryVOToEntity(umsResourceCategoryVO);
        umsResourceCategory.setCreateTime(new Date());
        return resourceCategoryMapper.insert(umsResourceCategory);
    }

    @Override
    public int update(Long id, UmsResourceCategoryVO umsResourceCategoryVO) {
        UmsResourceCategory umsResourceCategory = UmsResourceCategoryConvert.INSTANCE.umsResourceCategoryVOToEntity(umsResourceCategoryVO);
        umsResourceCategory.setId(id);
        return resourceCategoryMapper.updateByPrimaryKeySelective(umsResourceCategory);
    }

    @Override
    public int delete(Long id) {
        return resourceCategoryMapper.deleteByPrimaryKey(id);
    }
}

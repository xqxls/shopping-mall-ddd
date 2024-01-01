package com.xqxls.repository.pms;

import com.github.pagehelper.PageHelper;
import com.xqxls.convert.pms.CmsSubjectConvert;
import com.xqxls.mapper.CmsSubjectMapper;
import com.xqxls.model.CmsSubject;
import com.xqxls.pms.model.vo.CmsSubjectVO;
import com.xqxls.pms.repository.ICmsSubjectRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/30 20:00
 */
@Repository
public class CmsSubjectRepository implements ICmsSubjectRepository {

    @Resource
    private CmsSubjectMapper cmsSubjectMapper;

    @Override
    public List<CmsSubjectVO> listAll() {
        return CmsSubjectConvert.INSTANCE.cmsSubjectEntityToVOList(cmsSubjectMapper.selectByExample(new Example(CmsSubject.class)));
    }

    @Override
    public List<CmsSubjectVO> list(String keyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(CmsSubject.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.hasText(keyword)) {
            criteria.andLike("title","%" + keyword + "%");
        }
        return CmsSubjectConvert.INSTANCE.cmsSubjectEntityToVOList(cmsSubjectMapper.selectByExample(example));
    }
}

package com.xqxls.pms.service.impl;

import com.xqxls.pms.model.vo.CmsSubjectVO;
import com.xqxls.pms.repository.ICmsSubjectRepository;
import com.xqxls.pms.service.CmsSubjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品专题管理Service实现类
 * Created by xqxls on 2018/6/1.
 */
@Service
public class CmsSubjectServiceImpl implements CmsSubjectService {
    @Resource
    private ICmsSubjectRepository cmsSubjectRepository;

    @Override
    public List<CmsSubjectVO> listAll() {
        return cmsSubjectRepository.listAll();
    }

    @Override
    public List<CmsSubjectVO> list(String keyword, Integer pageNum, Integer pageSize) {
        return cmsSubjectRepository.list(keyword,pageNum,pageSize);
    }
}

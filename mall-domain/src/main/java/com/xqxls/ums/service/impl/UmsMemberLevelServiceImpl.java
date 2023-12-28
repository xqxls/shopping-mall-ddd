package com.xqxls.ums.service.impl;

import com.xqxls.ums.model.vo.UmsMemberLevelVO;
import com.xqxls.ums.repository.IUmsMemberLevelRepository;
import com.xqxls.ums.service.UmsMemberLevelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 会员等级管理Service实现类
 * Created by xqxls on 2018/4/26.
 */
@Service
public class UmsMemberLevelServiceImpl implements UmsMemberLevelService {
    @Resource
    private IUmsMemberLevelRepository umsMemberLevelRepository;
    @Override
    public List<UmsMemberLevelVO> list(Integer defaultStatus) {
        return umsMemberLevelRepository.list(defaultStatus);
    }
}

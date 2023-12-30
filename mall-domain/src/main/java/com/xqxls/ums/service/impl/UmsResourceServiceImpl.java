package com.xqxls.ums.service.impl;

import com.xqxls.ums.model.vo.UmsResourceVO;
import com.xqxls.ums.repository.IUmsResourceRepository;
import com.xqxls.ums.service.UmsResourceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 后台资源管理Service实现类
 * Created by xqxls on 2020/2/2.
 */
@Service
public class UmsResourceServiceImpl implements UmsResourceService {

    @Resource
    private IUmsResourceRepository umsResourceRepository;

    @Override
    public int create(UmsResourceVO umsResourceVO) {
        return umsResourceRepository.create(umsResourceVO);
    }

    @Override
    public int update(Long id, UmsResourceVO umsResourceVO) {
        return umsResourceRepository.update(id,umsResourceVO);
    }

    @Override
    public UmsResourceVO getItem(Long id) {
        return umsResourceRepository.getItem(id);
    }

    @Override
    public int delete(Long id) {
        return umsResourceRepository.delete(id);
    }

    @Override
    public List<UmsResourceVO> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum) {
        return umsResourceRepository.list(categoryId,nameKeyword,urlKeyword,pageSize,pageNum);
    }

    @Override
    public List<UmsResourceVO> listAll() {
        return umsResourceRepository.listAll();
    }

    @Override
    public Map<String, List<String>> initResourceRolesMap() {
        return umsResourceRepository.initResourceRolesMap();
    }
}

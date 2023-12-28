package com.xqxls.ums.service.impl;

import com.xqxls.ums.model.vo.UmsResourceCategoryVO;
import com.xqxls.ums.repository.IUmsResourceCategoryRepository;
import com.xqxls.ums.service.UmsResourceCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 后台资源分类管理Service实现类
 * Created by xqxls on 2020/2/5.
 */
@Service
public class UmsResourceCategoryServiceImpl implements UmsResourceCategoryService {
    @Resource
    private IUmsResourceCategoryRepository umsResourceCategoryRepository;

    @Override
    public List<UmsResourceCategoryVO> listAll() {
        return umsResourceCategoryRepository.listAll();
    }

    @Override
    public int create(UmsResourceCategoryVO umsResourceCategoryVO) {
        return umsResourceCategoryRepository.create(umsResourceCategoryVO);
    }

    @Override
    public int update(Long id, UmsResourceCategoryVO umsResourceCategoryVO) {
        return umsResourceCategoryRepository.update(id,umsResourceCategoryVO);
    }

    @Override
    public int delete(Long id) {
        return umsResourceCategoryRepository.delete(id);
    }
}

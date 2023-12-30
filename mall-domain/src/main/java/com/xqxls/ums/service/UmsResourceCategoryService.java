package com.xqxls.ums.service;

import com.xqxls.ums.model.vo.UmsResourceCategoryVO;

import java.util.List;

/**
 * 后台资源分类管理Service
 * Created by xqxls on 2020/2/5.
 */
public interface UmsResourceCategoryService {

    /**
     * 获取所有资源分类
     */
    List<UmsResourceCategoryVO> listAll();

    /**
     * 创建资源分类
     */
    int create(UmsResourceCategoryVO umsResourceCategoryVO);

    /**
     * 修改资源分类
     */
    int update(Long id, UmsResourceCategoryVO umsResourceCategoryVO);

    /**
     * 删除资源分类
     */
    int delete(Long id);
}

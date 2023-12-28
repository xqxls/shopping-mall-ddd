package com.xqxls.ums.repository;

import com.xqxls.model.UmsResourceCategory;
import com.xqxls.ums.model.vo.UmsResourceCategoryVO;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/27 21:46
 */
public interface IUmsResourceCategoryRepository {

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

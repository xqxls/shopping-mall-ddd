package com.xqxls.ums.repository;

import com.xqxls.ums.model.vo.UmsMenuVO;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/27 14:39
 */
public interface IUmsMenuRepository {

    /**
     * 创建后台菜单
     */
    int create(UmsMenuVO UmsMenuVO);

    /**
     * 修改后台菜单
     */
    int update(Long id, UmsMenuVO UmsMenuVO);

    /**
     * 根据ID获取菜单详情
     */
    UmsMenuVO getItem(Long id);

    /**
     * 根据ID删除菜单
     */
    int delete(Long id);

    /**
     * 分页查询后台菜单
     */
    List<UmsMenuVO> list(Long parentId, Integer pageSize, Integer pageNum);

    /**
     * 查询后台菜单
     */
    List<UmsMenuVO> list();

    /**
     * 修改菜单显示状态
     */
    int updateHidden(Long id, Integer hidden);
}

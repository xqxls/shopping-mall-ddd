package com.xqxls.ums.service;

import com.xqxls.ums.model.res.UmsMenuNodeResult;
import com.xqxls.ums.model.vo.UmsMenuVO;

import java.util.List;

/**
 * 后台菜单管理Service
 * Created by xqxls on 2020/2/2.
 */
public interface UmsMenuService {

    /**
     * 创建后台菜单
     */
    int create(UmsMenuVO umsMenuVO);

    /**
     * 修改后台菜单
     */
    int update(Long id, UmsMenuVO umsMenuVO);

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

    /**
     * 树形结构返回所有菜单列表
     */
    List<UmsMenuNodeResult> treeList();

}

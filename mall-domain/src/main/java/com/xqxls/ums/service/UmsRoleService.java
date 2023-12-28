package com.xqxls.ums.service;

import com.xqxls.ums.model.vo.UmsMenuVO;
import com.xqxls.ums.model.vo.UmsResourceVO;
import com.xqxls.ums.model.vo.UmsRoleVO;

import java.util.List;

/**
 * 后台角色管理Service
 * Created by xqxls on 2018/9/30.
 */
public interface UmsRoleService {

    /**
     * 添加角色
     */
    int create(UmsRoleVO role);

    /**
     * 修改角色信息
     */
    int update(Long id, UmsRoleVO role);

    /**
     * 批量删除角色
     */
    int delete(List<Long> ids);

    /**
     * 获取所有角色列表
     */
    List<UmsRoleVO> list();

    /**
     * 分页获取角色列表
     */
    List<UmsRoleVO> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 根据管理员ID获取对应菜单
     */
    List<UmsMenuVO> getMenuList(Long adminId);

    /**
     * 获取角色相关菜单
     */
    List<UmsMenuVO> listMenu(Long roleId);

    /**
     * 获取角色相关资源
     */
    List<UmsResourceVO> listResource(Long roleId);

    /**
     * 给角色分配菜单
     */
    int allocMenu(Long roleId, List<Long> menuIds);

    /**
     * 给角色分配资源
     */
    int allocResource(Long roleId, List<Long> resourceIds);
}

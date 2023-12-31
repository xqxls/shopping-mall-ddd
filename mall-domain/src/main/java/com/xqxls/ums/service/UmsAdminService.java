package com.xqxls.ums.service;

import com.xqxls.api.CommonResult;
import com.xqxls.ums.model.req.UmsAdminReq;
import com.xqxls.ums.model.req.UpdateAdminPasswordReq;
import com.xqxls.ums.model.vo.UmsAdminVO;
import com.xqxls.ums.model.vo.UmsResourceVO;
import com.xqxls.ums.model.vo.UmsRoleVO;

import java.util.List;
import java.util.Map;

/**
 * 后台管理员Service
 * Created by xqxls on 2018/4/26.
 */
public interface UmsAdminService {
    /**
     * 根据用户名获取用户
     */
    UmsAdminVO getAdminByUsername(String username);

    /**
     * 注册功能
     */
    UmsAdminVO register(UmsAdminReq umsAdminReq);


    /**
     * 根据用户id获取用户
     */
    UmsAdminVO getItem(Long id);

    /**
     * 根据用户名或昵称分页查询用户
     */
    List<UmsAdminVO> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 修改指定用户信息
     */
    int update(Long id, UmsAdminVO umsAdminVO);

    /**
     * 删除指定用户
     */
    int delete(Long id);

    /**
     * 修改用户角色关系
     */
    int updateRole(Long adminId, List<Long> roleIds);

    /**
     * 获取用户对于角色
     */
    List<UmsRoleVO> getRoleList(Long adminId);

    /**
     * 获取指定用户的可访问资源
     */
    List<UmsResourceVO> getResourceList(Long adminId);

    /**
     * 修改密码
     */
    int updatePassword(UpdateAdminPasswordReq updateAdminPasswordReq);

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return token
     */
    Map<String,String> login(String username, String password);
}

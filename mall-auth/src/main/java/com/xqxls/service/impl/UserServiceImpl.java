package com.xqxls.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.xqxls.constant.AuthConstant;
import com.xqxls.constant.MessageConstant;
import com.xqxls.domain.UserDto;
import com.xqxls.exception.ApiException;
import com.xqxls.feign.UmsAdminFeign;
import com.xqxls.feign.UmsMemberFeign;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户管理业务类
 * Created by xqxls on 2020/6/19.
 */
@Service
public class UserServiceImpl {

    @Resource
    private HttpServletRequest request;
    @Resource
    private UmsAdminFeign umsAdminFeign;
    @Resource
    private UmsMemberFeign umsMemberFeign;

    public UserDto loadUserByUsername(String username) {
        String clientId = request.getParameter("client_id");
        UserDto userDto;
        if(AuthConstant.ADMIN_CLIENT_ID.equals(clientId)){
            userDto = umsAdminFeign.loadUserByUsername(username).getData();
        }else{
            userDto = umsMemberFeign.loadUserByUsername(username).getData();
        }
        if (userDto==null) {
            throw new ApiException(MessageConstant.USERNAME_PASSWORD_ERROR);
        }
        userDto.setClientId(clientId);
        return userDto;
    }

    public SaTokenInfo login(String username, String password) {
        SaTokenInfo saTokenInfo = null;
        UserDto userDto = this.loadUserByUsername(username);
        if (userDto == null) {
            return null;
        }
        if (!SaSecureUtil.md5(password).equals(userDto.getPassword())) {
            return null;
        }
        // 密码校验成功后登录，一行代码实现登录
        StpUtil.login(userDto.getId());
        // 将用户信息存储到Session中
        StpUtil.getSession().set("userInfo",userDto);
        // 获取当前登录用户Token信息
        saTokenInfo = StpUtil.getTokenInfo();
        return saTokenInfo;
    }

}

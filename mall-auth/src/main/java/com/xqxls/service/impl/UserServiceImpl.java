package com.xqxls.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.xqxls.domain.UserDto;
import com.xqxls.feign.UmsAdminFeign;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户管理业务类
 * Created by xqxls on 2020/6/19.
 */
@Service
public class UserServiceImpl {

    @Resource
    private UmsAdminFeign umsAdminFeign;

    public UserDto loadUserByUsername(String username) {
        return umsAdminFeign.loadUserByUsername(username).getData();
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

package com.xqxls.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.xqxls.domain.UserDto;
import com.xqxls.feign.UmsAdminFeign;
import com.xqxls.response.UmsAdminRpcResponse;
import com.xqxls.response.UmsResourceRpcResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 用户管理业务类
 * Created by xqxls on 2020/6/19.
 */
@Service
public class UserServiceImpl {

    @Autowired
    private UmsAdminFeign umsAdminFeign;

    public UserDto loadUserByUsername(String username) {
        UmsAdminRpcResponse umsAdminRpcResponse = umsAdminFeign.getAdminByUsername(username).getData();
        if (Objects.isNull(umsAdminRpcResponse)) {
            return null;
        }
        List<UmsResourceRpcResponse> resourceList = umsAdminFeign.getResourceList(umsAdminRpcResponse.getId()).getData();
        return UserDto.builder()
                .id(umsAdminRpcResponse.getId())
                .username(umsAdminRpcResponse.getUsername())
                .password(umsAdminRpcResponse.getPassword())
                .permissionList(resourceList.stream().map(UmsResourceRpcResponse::getUrl).collect(Collectors.toList()))
                .build();
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

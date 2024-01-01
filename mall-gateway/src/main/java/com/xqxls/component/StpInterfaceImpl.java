package com.xqxls.component;

import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import com.xqxls.domain.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义权限验证接口扩展
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 返回此 loginId 拥有的权限码列表
        UserDto userDto = (UserDto) StpUtil.getSession().get("userInfo");
        return userDto.getPermissionList();
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 返回此 loginId 拥有的角色码列表
        UserDto userDto = (UserDto) StpUtil.getSession().get("userInfo");
        return userDto.getRoles();
    }

}


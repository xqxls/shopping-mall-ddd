package com.xqxls.util;

import cn.dev33.satoken.stp.StpUtil;
import com.xqxls.domain.UserDto;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/26 17:22
 */
public class SecurityUtil {

    public static UserDto getCurrentUser(){
        return (UserDto) StpUtil.getSession().get("userInfo");
    }
}

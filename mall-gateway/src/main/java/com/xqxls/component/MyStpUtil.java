package com.xqxls.component;

import cn.dev33.satoken.exception.NotRoleException;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/31 21:33
 */
public class MyStpUtil extends StpUtil {

    public static void containsRole(List<String> roleList) {
        boolean f = roleList.stream().anyMatch(stpLogic::hasRole);
        if(!f){
            throw new NotRoleException(JSON.toJSONString(roleList), stpLogic.loginType);
        }
    }
}

package com.xqxls.constant;

/**
 * 权限相关常量定义
 * Created by xqxls on 2020/6/19.
 */
public interface AuthConstant {

    /**
     * 后台管理接口路径匹配
     */
    String ADMIN_URL_PATTERN = "/mall-admin/**";

    /**
     * Redis缓存权限规则key
     */
    String RESOURCE_ROLES_MAP_KEY = "auth:resourceRolesMap";


}

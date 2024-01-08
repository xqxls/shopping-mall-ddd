package com.xqxls.constant;

/**
 * 权限相关常量定义
 * Created by xqxls on 2020/6/19.
 */
public interface AuthConstant {

    /**
     * 后台管理接口路径匹配
     */
    String ADMIN_URL_PATTERN = "/mall-domain/**";

    /**
     * 用户信息Http请求头
     */
    String USER_TOKEN_HEADER = "user";

    /**
     * Redis缓存权限规则key
     */
    String RESOURCE_ROLES_MAP_KEY = "auth:resourceRolesMap";

    /**
     * 后台管理client_id
     */
    String ADMIN_CLIENT_ID = "admin-app";

    /**
     * 前台商城client_id
     */
    String PORTAL_CLIENT_ID = "portal-app";


}

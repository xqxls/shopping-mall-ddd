package com.xqxls.component;

import com.xqxls.ums.service.UmsResourceService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 资源与角色访问对应关系操作组件
 * Created by xqxls on 2020/7/17.
 */
@Component
public class ResourceRoleRulesHolder {

    @Resource
    private UmsResourceService resourceService;

    @PostConstruct
    public void initResourceRolesMap(){
        resourceService.initResourceRolesMap();
    }
}

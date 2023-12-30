package com.xqxls.config;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.context.model.SaRequest;
import cn.dev33.satoken.reactor.context.SaReactorSyncHolder;
import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.hutool.core.util.URLUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Configuration
public class SaTokenConfig {

    @Resource
    private IgnoreUrlsConfig ignoreUrlsConfig;

    /**
     * 注册Sa-Token全局过滤器
     */
    @Bean
    public SaReactorFilter getSaReactorFilter() {
        return new SaReactorFilter()
                // 拦截地址
                .addInclude("/**")
                // 开放地址
                .addExclude("/favicon.ico")
                // 鉴权方法：每次访问进入
                .setAuth(r -> {
                    // 获取配置文件中的白名单路径
                    List<String> ignoreUrls = ignoreUrlsConfig.getUrls();
                    // 登录认证：除登录接口都需要认证
                    SaRouter.match(Collections.singletonList("/**"), ignoreUrls, StpUtil::checkLogin);
                    // 权限认证：不同接口访问权限不同
                    SaRequest request = SaHolder.getRequest();
                    String url = request.getUrl();
                    String path = URLUtil.getPath(url);
                    if(!ignoreUrls.contains(path)){
                        SaRouter.match(path, () -> StpUtil.checkPermission(path));
                    }
                })
                // setAuth方法异常处理
                .setError(e -> {
                    //设置错误返回格式为JSON
                    ServerWebExchange exchange = SaReactorSyncHolder.getContent();
                    exchange.getResponse().getHeaders().set("Content-Type", "application/json; charset=utf-8");
                    return SaResult.error(e.getMessage());
                });
    }
}

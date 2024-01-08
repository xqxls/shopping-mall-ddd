package com.xqxls.config;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.context.model.SaRequest;
import cn.dev33.satoken.reactor.context.SaReactorSyncHolder;
import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.URLUtil;
import com.xqxls.component.MyStpUtil;
import com.xqxls.constant.AuthConstant;
import com.xqxls.service.RedisService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.*;

@Configuration
public class SaTokenConfig {

    @Resource
    private IgnoreUrlsConfig ignoreUrlsConfig;
    @Resource
    private RedisService redisService;

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
                    PathMatcher pathMatcher = new AntPathMatcher();
                    //非管理端路径直接放行
                    if (!pathMatcher.match(AuthConstant.ADMIN_URL_PATTERN, path)) {
                        return;
                    }
                    if(!ignoreUrls.contains(path)){
                        Map<Object, Object> resourceRolesMap = redisService.hGetAll(AuthConstant.RESOURCE_ROLES_MAP_KEY);
                        Iterator<Object> iterator = resourceRolesMap.keySet().iterator();
                        List<String> authorities = new ArrayList<>();
                        while (iterator.hasNext()) {
                            String pattern = (String) iterator.next();
                            if (pathMatcher.match(pattern, path)) {
                                authorities.addAll(Convert.toList(String.class, resourceRolesMap.get(pattern)));
                            }
                        }
                        SaRouter.match("/**", () -> MyStpUtil.containsRole(authorities));
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

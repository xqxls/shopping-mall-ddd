package com.xqxls.feign;

import com.xqxls.api.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/31 16:10
 */
@FeignClient(name = "mall-auth")
public interface AuthFeign {

    @PostMapping(value = "/oauth/token")
    CommonResult<Map<String,String>> login(@RequestParam String username, @RequestParam String password);
}

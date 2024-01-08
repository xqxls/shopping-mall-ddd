package com.xqxls.feign;

import com.xqxls.api.CommonResult;
import com.xqxls.domain.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/7 17:27
 */
@FeignClient(name = "mall-portal")
public interface UmsMemberFeign {

    @GetMapping(value = "/sso/loadByUsername")
    CommonResult<UserDto> loadUserByUsername(@RequestParam(value = "username") String username);
}

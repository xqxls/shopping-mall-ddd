package com.xqxls.feign;

import com.xqxls.api.CommonResult;
import com.xqxls.response.UmsResourceRpcResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "mall-admin", path = "/resource")
public interface UmsResourceFeign {

    @GetMapping(value = "/findAll")
    CommonResult<List<UmsResourceRpcResponse>> findAll();
}

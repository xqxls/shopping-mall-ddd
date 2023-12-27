package com.xqxls.feign;

import com.xqxls.api.CommonResult;
import com.xqxls.response.UmsAdminRpcResponse;
import com.xqxls.response.UmsResourceRpcResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "mall-domain")
public interface UmsAdminFeign {

    @GetMapping(value = "/admin/getAdminByUsername")
    CommonResult<UmsAdminRpcResponse> getAdminByUsername(@RequestParam(value = "username") String username);

    @GetMapping(value = "/admin/getResourceList")
    CommonResult<List<UmsResourceRpcResponse>> getResourceList(@RequestParam(value = "adminId") Long adminId);
}

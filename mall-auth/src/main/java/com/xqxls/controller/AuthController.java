package com.xqxls.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.xqxls.api.CommonResult;
import com.xqxls.domain.UserDto;
import com.xqxls.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义Oauth2获取令牌接口
 * Created by xqxls on 2020/7/17.
 */
@RestController
@Api(tags = "AuthController", description = "认证中心登录认证")
@RequestMapping("/oauth")
public class AuthController {

    @Resource
    private UserServiceImpl userService;

    @ApiOperation(value = "登录认证")
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public CommonResult<Map<String,String>> login(@RequestParam String username, @RequestParam String password) {
        SaTokenInfo saTokenInfo = userService.login(username, password);
        if (saTokenInfo == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", saTokenInfo.getTokenValue());
        tokenMap.put("tokenHead", saTokenInfo.getTokenName());
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "获取当前登录用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public CommonResult<UserDto> getAdminInfo() {
        UserDto userDto = (UserDto) StpUtil.getSession().get("userInfo");
        return CommonResult.success(userDto);
    }
}

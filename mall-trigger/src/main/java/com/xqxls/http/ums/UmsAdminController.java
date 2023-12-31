package com.xqxls.http.ums;

import cn.hutool.core.collection.CollUtil;
import com.xqxls.api.CommonPage;
import com.xqxls.api.CommonResult;
import com.xqxls.domain.UserDto;
import com.xqxls.model.UmsRole;
import com.xqxls.response.UmsAdminRpcResponse;
import com.xqxls.response.UmsResourceRpcResponse;
import com.xqxls.ums.model.req.UmsAdminLoginReq;
import com.xqxls.ums.model.req.UmsAdminReq;
import com.xqxls.ums.model.req.UpdateAdminPasswordReq;
import com.xqxls.ums.model.vo.UmsAdminVO;
import com.xqxls.ums.model.vo.UmsResourceVO;
import com.xqxls.ums.model.vo.UmsRoleVO;
import com.xqxls.ums.service.UmsAdminService;
import com.xqxls.ums.service.UmsRoleService;
import com.xqxls.util.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 后台用户管理
 * Created by xqxls on 2018/4/26.
 */
@Controller
@Api(tags = "UmsAdminController", description = "后台用户管理")
@RequestMapping("/admin")
public class UmsAdminController {
    @Resource
    private UmsAdminService adminService;

    @Resource
    private UmsRoleService roleService;

    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<UmsAdminVO> register(@Validated @RequestBody UmsAdminReq umsAdminReq) {
        UmsAdminVO umsAdminVO = adminService.register(umsAdminReq);
        if (umsAdminVO == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(umsAdminVO);
    }

    @ApiOperation(value = "登录以后返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Map<String,String>> login(@Validated @RequestBody UmsAdminLoginReq umsAdminLoginReq) {
        Map<String,String> result = adminService.login(umsAdminLoginReq.getUsername(),umsAdminLoginReq.getPassword());
        if (result == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(result);
    }

    @ApiOperation("根据用户名或姓名分页获取用户列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<UmsAdminVO>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<UmsAdminVO> adminList = adminService.list(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(adminList));
    }

    @ApiOperation("获取指定用户信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<UmsAdminVO> getItem(@PathVariable Long id) {
        UmsAdminVO adminVO = adminService.getItem(id);
        return CommonResult.success(adminVO);
    }

    @ApiOperation("修改指定用户信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> update(@PathVariable Long id, @RequestBody UmsAdminVO adminVO) {
        int count = adminService.update(id, adminVO);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改指定用户密码")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> updatePassword(@RequestBody UpdateAdminPasswordReq updateAdminPasswordReq) {
        int status = adminService.updatePassword(updateAdminPasswordReq);
        if (status > 0) {
            return CommonResult.success(status);
        } else if (status == -1) {
            return CommonResult.failed("提交参数不合法");
        } else if (status == -2) {
            return CommonResult.failed("找不到该用户");
        } else if (status == -3) {
            return CommonResult.failed("旧密码错误");
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("删除指定用户信息")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> delete(@PathVariable Long id) {
        int count = adminService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改帐号状态")
    @RequestMapping(value = "/updateStatus/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> updateStatus(@PathVariable Long id,@RequestParam(value = "status") Integer status) {
        UmsAdminVO umsAdminVO = new UmsAdminVO();
        umsAdminVO.setStatus(status);
        int count = adminService.update(id,umsAdminVO);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("给用户分配角色")
    @RequestMapping(value = "/role/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> updateRole(@RequestParam("adminId") Long adminId,
                                   @RequestParam("roleIds") List<Long> roleIds) {
        int count = adminService.updateRole(adminId, roleIds);
        if (count >= 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取指定用户的角色")
    @RequestMapping(value = "/role/{adminId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsRoleVO>> getRoleList(@PathVariable Long adminId) {
        List<UmsRoleVO> roleList = adminService.getRoleList(adminId);
        return CommonResult.success(roleList);
    }


    @ApiOperation(value = "获取当前登录用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Map<String, Object>> getAdminInfo() {
        UserDto userDto = SecurityUtil.getCurrentUser();
        Map<String, Object> data = new HashMap<>();
        data.put("username", userDto.getUsername());
        data.put("menus", roleService.getMenuList(userDto.getId()));
        data.put("icon", userDto.getIcon());
        List<UmsRoleVO> roleList = adminService.getRoleList(userDto.getId());
        if(CollUtil.isNotEmpty(roleList)){
            List<String> roles = roleList.stream().map(UmsRoleVO::getName).collect(Collectors.toList());
            data.put("roles",roles);
        }
        return CommonResult.success(data);
    }

    @ApiOperation("根据用户名获取用户")
    @RequestMapping(value = "/loadByUsername", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<UserDto> getAdminByUsername(@RequestParam(value = "username") String username) {
        return CommonResult.success(adminService.loadUserByUsername(username));
    }

    @ApiOperation("根据用户id获取资源")
    @RequestMapping(value = "/getResourceList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsResourceRpcResponse>> getResourceList(@RequestParam(value = "adminId") Long adminId) {
        List<UmsResourceRpcResponse> result = new ArrayList<>();
        List<UmsResourceVO> umsResourceVOList = adminService.getResourceList(adminId);
        umsResourceVOList.forEach(resource->{
            UmsResourceRpcResponse response = new UmsResourceRpcResponse();
            BeanUtils.copyProperties(resource,response);
            result.add(response);
        });
        return CommonResult.success(result);
    }
}

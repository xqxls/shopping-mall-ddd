package com.xqxls.http.ums;

import com.xqxls.api.CommonPage;
import com.xqxls.api.CommonResult;
import com.xqxls.ums.model.vo.UmsResourceVO;
import com.xqxls.ums.service.UmsResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 后台资源管理Controller
 * Created by xqxls on 2020/2/4.
 */
@Controller
@Api(tags = "UmsResourceController", description = "后台资源管理")
@RequestMapping("/resource")
public class UmsResourceController {

    @Resource
    private UmsResourceService resourceService;

    @ApiOperation("添加后台资源")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> create(@RequestBody UmsResourceVO umsResourceVO) {
        int count = resourceService.create(umsResourceVO);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("修改后台资源")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> update(@PathVariable Long id,
                               @RequestBody UmsResourceVO umsResourceVO) {
        int count = resourceService.update(id, umsResourceVO);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("根据ID获取资源详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<UmsResourceVO> getItem(@PathVariable Long id) {
        UmsResourceVO umsResourceVO = resourceService.getItem(id);
        return CommonResult.success(umsResourceVO);
    }

    @ApiOperation("根据ID删除后台资源")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> delete(@PathVariable Long id) {
        int count = resourceService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("分页模糊查询后台资源")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<UmsResourceVO>> list(@RequestParam(required = false) Long categoryId,
                                                      @RequestParam(required = false) String nameKeyword,
                                                      @RequestParam(required = false) String urlKeyword,
                                                      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<UmsResourceVO> resourceList = resourceService.list(categoryId,nameKeyword, urlKeyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(resourceList));
    }

    @ApiOperation("查询所有后台资源")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsResourceVO>> listAll() {
        List<UmsResourceVO> resourceList = resourceService.listAll();
        return CommonResult.success(resourceList);
    }

    @ApiOperation("初始化资源角色关联数据")
    @RequestMapping(value = "/initResourceRolesMap", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Object> initResourceRolesMap() {
        Map<String, List<String>> resourceRolesMap = resourceService.initResourceRolesMap();
        return CommonResult.success(resourceRolesMap);
    }
}

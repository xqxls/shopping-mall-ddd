package com.xqxls.http.pms;

import com.xqxls.api.CommonPage;
import com.xqxls.api.CommonResult;
import com.xqxls.pms.model.res.PmsProductAttributeCategoryItemResult;
import com.xqxls.pms.model.vo.PmsProductAttributeCategoryVO;
import com.xqxls.pms.service.PmsProductAttributeCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品属性分类Controller
 * Created by xqxls on 2018/4/26.
 */
@Controller
@Api(tags = "PmsProductAttributeCategoryController", description = "商品属性分类管理")
@RequestMapping("/productAttribute/category")
public class PmsProductAttributeCategoryController {
    @Resource
    private PmsProductAttributeCategoryService productAttributeCategoryService;

    @ApiOperation("添加商品属性分类")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> create(@RequestParam String name) {
        int count = productAttributeCategoryService.create(name);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("修改商品属性分类")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> update(@PathVariable Long id, @RequestParam String name) {
        int count = productAttributeCategoryService.update(id, name);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("删除单个商品属性分类")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Object> delete(@PathVariable Long id) {
        int count = productAttributeCategoryService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("获取单个商品属性分类信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsProductAttributeCategoryVO> getItem(@PathVariable Long id) {
        PmsProductAttributeCategoryVO pmsProductAttributeCategoryVO = productAttributeCategoryService.getItem(id);
        return CommonResult.success(pmsProductAttributeCategoryVO);
    }

    @ApiOperation("分页获取所有商品属性分类")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsProductAttributeCategoryVO>> getList(@RequestParam(defaultValue = "5") Integer pageSize, @RequestParam(defaultValue = "1") Integer pageNum) {
        List<PmsProductAttributeCategoryVO> productAttributeCategoryList = productAttributeCategoryService.getList(pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(productAttributeCategoryList));
    }

    @ApiOperation("获取所有商品属性分类及其下属性")
    @RequestMapping(value = "/list/withAttr", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsProductAttributeCategoryItemResult>> getListWithAttr() {
        List<PmsProductAttributeCategoryItemResult> productAttributeCategoryResultList = productAttributeCategoryService.getListWithAttr();
        return CommonResult.success(productAttributeCategoryResultList);
    }
}

package com.xqxls.http.pms;

import com.xqxls.api.CommonPage;
import com.xqxls.api.CommonResult;
import com.xqxls.pms.model.req.PmsProductCategoryReq;
import com.xqxls.pms.model.res.PmsProductCategoryWithChildrenItemResult;
import com.xqxls.pms.model.vo.PmsProductCategoryVO;
import com.xqxls.pms.service.PmsProductCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品分类模块Controller
 * Created by xqxls on 2018/4/26.
 */
@Controller
@Api(tags = "PmsProductCategoryController", description = "商品分类管理")
@RequestMapping("/productCategory")
public class PmsProductCategoryController {
    @Resource
    private PmsProductCategoryService productCategoryService;

    @ApiOperation("添加产品分类")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> create(@Validated @RequestBody PmsProductCategoryReq productCategoryReq) {
        int count = productCategoryService.create(productCategoryReq);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("修改商品分类")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> update(@PathVariable Long id,
                         @Validated
                         @RequestBody PmsProductCategoryReq productCategoryReq) {
        int count = productCategoryService.update(id, productCategoryReq);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("分页查询商品分类")
    @RequestMapping(value = "/list/{parentId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsProductCategoryVO>> getList(@PathVariable Long parentId,
                                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<PmsProductCategoryVO> productCategoryList = productCategoryService.getList(parentId, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(productCategoryList));
    }

    @ApiOperation("根据id获取商品分类")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsProductCategoryVO> getItem(@PathVariable Long id) {
        PmsProductCategoryVO productCategoryVO = productCategoryService.getItem(id);
        return CommonResult.success(productCategoryVO);
    }

    @ApiOperation("删除商品分类")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> delete(@PathVariable Long id) {
        int count = productCategoryService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("修改导航栏显示状态")
    @RequestMapping(value = "/update/navStatus", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> updateNavStatus(@RequestParam("ids") List<Long> ids, @RequestParam("navStatus") Integer navStatus) {
        int count = productCategoryService.updateNavStatus(ids, navStatus);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("修改显示状态")
    @RequestMapping(value = "/update/showStatus", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> updateShowStatus(@RequestParam("ids") List<Long> ids, @RequestParam("showStatus") Integer showStatus) {
        int count = productCategoryService.updateShowStatus(ids, showStatus);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("查询所有一级分类及子分类")
    @RequestMapping(value = "/list/withChildren", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsProductCategoryWithChildrenItemResult>> listWithChildren() {
        List<PmsProductCategoryWithChildrenItemResult> list = productCategoryService.listWithChildren();
        return CommonResult.success(list);
    }
}

package com.xqxls.http.pms;

import com.xqxls.api.CommonPage;
import com.xqxls.api.CommonResult;
import com.xqxls.pms.model.req.PmsBrandReq;
import com.xqxls.pms.model.vo.PmsBrandVO;
import com.xqxls.pms.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 品牌功能Controller
 * Created by xqxls on 2018/4/26.
 */
@Controller
@Api(tags = "PmsBrandController", description = "商品品牌管理")
@RequestMapping("/brand")
public class PmsBrandController {
    @Resource
    private PmsBrandService brandService;

    @ApiOperation(value = "获取全部品牌列表")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsBrandVO>> getList() {
        return CommonResult.success(brandService.listAllBrand());
    }

    @ApiOperation(value = "添加品牌")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> create(@Validated @RequestBody PmsBrandReq pmsBrandReq) {
        int count = brandService.createBrand(pmsBrandReq);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "更新品牌")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> update(@PathVariable("id") Long id,
                               @Validated @RequestBody PmsBrandReq pmsBrandReq) {
        int count = brandService.updateBrand(id, pmsBrandReq);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "删除品牌")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Object> delete(@PathVariable("id") Long id) {
        int count = brandService.deleteBrand(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "根据品牌名称分页获取品牌列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsBrandVO>> getList(@RequestParam(value = "keyword", required = false) String keyword,
                                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        List<PmsBrandVO> brandList = brandService.listBrand(keyword, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(brandList));
    }

    @ApiOperation(value = "根据编号查询品牌信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsBrandVO> getItem(@PathVariable("id") Long id) {
        return CommonResult.success(brandService.getBrand(id));
    }

    @ApiOperation(value = "批量删除品牌")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> deleteBatch(@RequestParam("ids") List<Long> ids) {
        int count = brandService.deleteBrand(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "批量更新显示状态")
    @RequestMapping(value = "/update/showStatus", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> updateShowStatus(@RequestParam("ids") List<Long> ids,
                                   @RequestParam("showStatus") Integer showStatus) {
        int count = brandService.updateShowStatus(ids, showStatus);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "批量更新厂家制造商状态")
    @RequestMapping(value = "/update/factoryStatus", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> updateFactoryStatus(@RequestParam("ids") List<Long> ids,
                                      @RequestParam("factoryStatus") Integer factoryStatus) {
        int count = brandService.updateFactoryStatus(ids, factoryStatus);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
}

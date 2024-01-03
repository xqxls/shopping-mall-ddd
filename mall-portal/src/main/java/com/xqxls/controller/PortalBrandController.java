package com.xqxls.controller;

import com.xqxls.api.CommonPage;
import com.xqxls.api.CommonResult;
import com.xqxls.domain.product.model.vo.PmsBrandVO;
import com.xqxls.domain.product.model.vo.PmsProductVO;
import com.xqxls.domain.product.service.PortalBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 首页品牌推荐管理Controller
 * Created by macro on 2020/5/15.
 */
@Controller
@Api(tags = "PortalBrandController", description = "前台品牌管理")
@RequestMapping("/brand")
public class PortalBrandController {

    @Resource
    private PortalBrandService homeBrandService;

    @ApiOperation("分页获取推荐品牌")
    @RequestMapping(value = "/recommendList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsBrandVO>> recommendList(@RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize,
                                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<PmsBrandVO> brandList = homeBrandService.recommendList(pageNum, pageSize);
        return CommonResult.success(brandList);
    }

    @ApiOperation("获取品牌详情")
    @RequestMapping(value = "/detail/{brandId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsBrandVO> detail(@PathVariable Long brandId) {
        PmsBrandVO brand = homeBrandService.detail(brandId);
        return CommonResult.success(brand);
    }

    @ApiOperation("分页获取品牌相关商品")
    @RequestMapping(value = "/productList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsProductVO>> productList(@RequestParam Long brandId,
                                                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                              @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize) {
        CommonPage<PmsProductVO> result = CommonPage.restPage(homeBrandService.productList(brandId,pageNum, pageSize));
        return CommonResult.success(result);
    }
}

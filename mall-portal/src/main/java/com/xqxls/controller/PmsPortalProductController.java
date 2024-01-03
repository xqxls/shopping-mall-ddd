package com.xqxls.controller;

import com.xqxls.api.CommonPage;
import com.xqxls.api.CommonResult;
import com.xqxls.domain.product.model.aggregates.PmsPortalProductDetailRich;
import com.xqxls.domain.product.model.res.PmsProductCategoryNodeResult;
import com.xqxls.domain.product.model.vo.PmsProductVO;
import com.xqxls.domain.product.service.PmsPortalProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 前台商品管理Controller
 * Created by macro on 2020/4/6.
 */
@Controller
@Api(tags = "PmsPortalProductController", description = "前台商品管理")
@RequestMapping("/product")
public class PmsPortalProductController {

    @Resource
    private PmsPortalProductService portalProductService;

    @ApiOperation(value = "综合搜索、筛选、排序")
    @ApiImplicitParam(name = "sort", value = "排序字段:0->按相关度；1->按新品；2->按销量；3->价格从低到高；4->价格从高到低",
            defaultValue = "0", allowableValues = "0,1,2,3,4", paramType = "query", dataType = "integer")
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsProductVO>> search(@RequestParam(required = false) String keyword,
                                                       @RequestParam(required = false) Long brandId,
                                                       @RequestParam(required = false) Long productCategoryId,
                                                       @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                       @RequestParam(required = false, defaultValue = "5") Integer pageSize,
                                                       @RequestParam(required = false, defaultValue = "0") Integer sort) {
        List<PmsProductVO> productList = portalProductService.search(keyword, brandId, productCategoryId, pageNum, pageSize, sort);
        return CommonResult.success(CommonPage.restPage(productList));
    }

    @ApiOperation("以树形结构获取所有商品分类")
    @RequestMapping(value = "/categoryTreeList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsProductCategoryNodeResult>> categoryTreeList() {
        List<PmsProductCategoryNodeResult> list = portalProductService.categoryTreeList();
        return CommonResult.success(list);
    }

    @ApiOperation("获取前台商品详情")
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsPortalProductDetailRich> detail(@PathVariable Long id) {
        PmsPortalProductDetailRich productDetail = portalProductService.detail(id);
        return CommonResult.success(productDetail);
    }
}

package com.xqxls.controller;

import com.xqxls.api.CommonResult;
import com.xqxls.domain.product.model.aggregates.HomeContentRich;
import com.xqxls.domain.product.model.vo.CmsSubjectVO;
import com.xqxls.domain.product.model.vo.PmsProductCategoryVO;
import com.xqxls.domain.product.model.vo.PmsProductVO;
import com.xqxls.domain.product.service.HomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 首页内容管理Controller
 * Created by macro on 2019/1/28.
 */
@Controller
@Api(tags = "HomeController", description = "首页内容管理")
@RequestMapping("/home")
public class HomeController {
    @Resource
    private HomeService homeService;

    @ApiOperation("首页内容页信息展示")
    @RequestMapping(value = "/content", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<HomeContentRich> content() {
        HomeContentRich contentResult = homeService.content();
        return CommonResult.success(contentResult);
    }

    @ApiOperation("分页获取推荐商品")
    @RequestMapping(value = "/recommendProductList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsProductVO>> recommendProductList(@RequestParam(value = "pageSize", defaultValue = "4") Integer pageSize,
                                                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<PmsProductVO> productList = homeService.recommendProductList(pageSize, pageNum);
        return CommonResult.success(productList);
    }

    @ApiOperation("获取首页商品分类")
    @RequestMapping(value = "/productCateList/{parentId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsProductCategoryVO>> getProductCateList(@PathVariable Long parentId) {
        List<PmsProductCategoryVO> productCategoryList = homeService.getProductCateList(parentId);
        return CommonResult.success(productCategoryList);
    }

    @ApiOperation("根据分类获取专题")
    @RequestMapping(value = "/subjectList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<CmsSubjectVO>> getSubjectList(@RequestParam(required = false) Long cateId,
                                                           @RequestParam(value = "pageSize", defaultValue = "4") Integer pageSize,
                                                           @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<CmsSubjectVO> subjectList = homeService.getSubjectList(cateId,pageSize,pageNum);
        return CommonResult.success(subjectList);
    }

    @ApiOperation("分页获取人气推荐商品")
    @RequestMapping(value = "/hotProductList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsProductVO>> hotProductList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                         @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize) {
        List<PmsProductVO> productList = homeService.hotProductList(pageNum,pageSize);
        return CommonResult.success(productList);
    }

    @ApiOperation("分页获取新品推荐商品")
    @RequestMapping(value = "/newProductList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsProductVO>> newProductList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                           @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize) {
        List<PmsProductVO> productList = homeService.newProductList(pageNum,pageSize);
        return CommonResult.success(productList);
    }
}

package com.xqxls.http.pms;

import com.xqxls.api.CommonPage;
import com.xqxls.api.CommonResult;
import com.xqxls.pms.model.req.PmsProductAttributeReq;
import com.xqxls.pms.model.res.ProductAttrInfoResult;
import com.xqxls.pms.model.vo.PmsProductAttributeVO;
import com.xqxls.pms.service.PmsProductAttributeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品属性管理Controller
 * Created by xqxls on 2018/4/26.
 */
@Controller
@Api(tags = "PmsProductAttributeController", description = "商品属性管理")
@RequestMapping("/productAttribute")
public class PmsProductAttributeController {
    @Resource
    private PmsProductAttributeService productAttributeService;

    @ApiOperation("根据分类查询属性列表或参数列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "type", value = "0表示属性，1表示参数", required = true, paramType = "query", dataType = "integer")})
    @RequestMapping(value = "/list/{cid}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsProductAttributeVO>> getList(@PathVariable Long cid,
                                                                   @RequestParam(value = "type") Integer type,
                                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<PmsProductAttributeVO> productAttributeList = productAttributeService.getList(cid, type, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(productAttributeList));
    }

    @ApiOperation("添加商品属性信息")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> create(@RequestBody PmsProductAttributeReq productAttributeReq) {
        int count = productAttributeService.create(productAttributeReq);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("修改商品属性信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> update(@PathVariable Long id, @RequestBody PmsProductAttributeReq productAttributeReq) {
        int count = productAttributeService.update(id, productAttributeReq);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("查询单个商品属性")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsProductAttributeVO> getItem(@PathVariable Long id) {
        PmsProductAttributeVO productAttributeVO = productAttributeService.getItem(id);
        return CommonResult.success(productAttributeVO);
    }

    @ApiOperation("批量删除商品属性")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> delete(@RequestParam("ids") List<Long> ids) {
        int count = productAttributeService.delete(ids);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("根据商品分类的id获取商品属性及属性分类")
    @RequestMapping(value = "/attrInfo/{productCategoryId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<ProductAttrInfoResult>> getAttrInfo(@PathVariable Long productCategoryId) {
        List<ProductAttrInfoResult> productAttrInfoResultList = productAttributeService.getProductAttrInfo(productCategoryId);
        return CommonResult.success(productAttrInfoResultList);
    }
}

package com.xqxls.http.pms;

import com.xqxls.api.CommonPage;
import com.xqxls.api.CommonResult;
import com.xqxls.pms.model.aggregates.PmsProductRich;
import com.xqxls.pms.model.req.PmsProductReq;
import com.xqxls.pms.model.res.PmsProductUpdateResult;
import com.xqxls.pms.model.vo.PmsProductVO;
import com.xqxls.pms.service.PmsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品管理Controller
 * Created by xqxls on 2018/4/26.
 */
@Controller
@Api(tags = "PmsProductController", description = "商品管理")
@RequestMapping("/product")
public class PmsProductController {
    @Resource
    private PmsProductService productService;

    @ApiOperation("创建商品")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> create(@RequestBody PmsProductRich pmsProductRich) {
        int count = productService.create(pmsProductRich);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("根据商品id获取商品编辑信息")
    @RequestMapping(value = "/updateInfo/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsProductUpdateResult> getUpdateInfo(@PathVariable Long id) {
        PmsProductUpdateResult pmsProductUpdateResult = productService.getUpdateInfo(id);
        return CommonResult.success(pmsProductUpdateResult);
    }

    @ApiOperation("更新商品")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> update(@PathVariable Long id, @RequestBody PmsProductRich pmsProductRich) {
        int count = productService.update(id, pmsProductRich);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("查询商品")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsProductVO>> getList(PmsProductReq productReq,
                                                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<PmsProductVO> productList = productService.list(productReq, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(productList));
    }

    @ApiOperation("根据商品名称或货号模糊查询")
    @RequestMapping(value = "/simpleList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsProductVO>> getList(String keyword) {
        List<PmsProductVO> productList = productService.list(keyword);
        return CommonResult.success(productList);
    }

    @ApiOperation("批量修改审核状态")
    @RequestMapping(value = "/update/verifyStatus", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> updateVerifyStatus(@RequestParam("ids") List<Long> ids,
                                           @RequestParam("verifyStatus") Integer verifyStatus,
                                           @RequestParam("detail") String detail) {
        int count = productService.updateVerifyStatus(ids, verifyStatus, detail);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("批量上下架")
    @RequestMapping(value = "/update/publishStatus", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> updatePublishStatus(@RequestParam("ids") List<Long> ids,
                                            @RequestParam("publishStatus") Integer publishStatus) {
        int count = productService.updatePublishStatus(ids, publishStatus);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("批量推荐商品")
    @RequestMapping(value = "/update/recommendStatus", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> updateRecommendStatus(@RequestParam("ids") List<Long> ids,
                                              @RequestParam("recommendStatus") Integer recommendStatus) {
        int count = productService.updateRecommendStatus(ids, recommendStatus);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("批量设为新品")
    @RequestMapping(value = "/update/newStatus", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> updateNewStatus(@RequestParam("ids") List<Long> ids,
                                        @RequestParam("newStatus") Integer newStatus) {
        int count = productService.updateNewStatus(ids, newStatus);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("批量修改删除状态")
    @RequestMapping(value = "/update/deleteStatus", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> updateDeleteStatus(@RequestParam("ids") List<Long> ids,
                                           @RequestParam("deleteStatus") Integer deleteStatus) {
        int count = productService.updateDeleteStatus(ids, deleteStatus);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }
}

package com.xqxls.controller;

import com.xqxls.api.CommonResult;
import com.xqxls.domain.order.model.req.OmsOrderReturnApplyReq;
import com.xqxls.domain.order.service.OmsPortalOrderReturnApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 申请退货管理Controller
 * Created by macro on 2018/10/17.
 */
@Controller
@Api(tags = "OmsPortalOrderReturnApplyController", description = "申请退货管理")
@RequestMapping("/returnApply")
public class OmsPortalOrderReturnApplyController {
    @Resource
    private OmsPortalOrderReturnApplyService returnApplyService;

    @ApiOperation("申请退货")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> create(@RequestBody OmsOrderReturnApplyReq returnReq) {
        int count = returnApplyService.create(returnReq);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
}

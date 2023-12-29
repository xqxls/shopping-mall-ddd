package com.xqxls.http.oms;

import com.xqxls.api.CommonResult;
import com.xqxls.oms.model.vo.OmsOrderSettingVO;
import com.xqxls.oms.service.OmsOrderSettingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 订单设置Controller
 * Created by xqxls on 2018/10/16.
 */
@Controller
@Api(tags = "OmsOrderSettingController", description = "订单设置管理")
@RequestMapping("/orderSetting")
public class OmsOrderSettingController {
    @Resource
    private OmsOrderSettingService orderSettingService;

    @ApiOperation("获取指定订单设置")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<OmsOrderSettingVO> getItem(@PathVariable Long id) {
        OmsOrderSettingVO orderSettingVO = orderSettingService.getItem(id);
        return CommonResult.success(orderSettingVO);
    }

    @ApiOperation("修改指定订单设置")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> update(@PathVariable Long id, @RequestBody OmsOrderSettingVO orderSettingVO) {
        int count = orderSettingService.update(id,orderSettingVO);
        if(count>0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
}

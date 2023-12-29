package com.xqxls.http.oms;

import com.xqxls.api.CommonPage;
import com.xqxls.api.CommonResult;
import com.xqxls.oms.model.aggregates.OmsOrderReturnApplyRich;
import com.xqxls.oms.model.req.OmsReturnApplyReq;
import com.xqxls.oms.model.req.OmsUpdateStatusReq;
import com.xqxls.oms.model.vo.OmsOrderReturnApplyVO;
import com.xqxls.oms.service.OmsOrderReturnApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单退货申请管理
 * Created by xqxls on 2018/10/18.
 */
@Controller
@Api(tags = "OmsOrderReturnApplyController", description = "订单退货申请管理")
@RequestMapping("/returnApply")
public class OmsOrderReturnApplyController {
    @Resource
    private OmsOrderReturnApplyService returnApplyService;

    @ApiOperation("分页查询退货申请")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<OmsOrderReturnApplyVO>> list(OmsReturnApplyReq omsReturnApplyReq,
                                                                @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<OmsOrderReturnApplyVO> returnApplyVOList = returnApplyService.list(omsReturnApplyReq, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(returnApplyVOList));
    }

    @ApiOperation("批量删除申请")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> delete(@RequestParam("ids") List<Long> ids) {
        int count = returnApplyService.delete(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取退货申请详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Object> getItem(@PathVariable Long id) {
        OmsOrderReturnApplyRich omsOrderReturnApplyRich = returnApplyService.getItem(id);
        return CommonResult.success(omsOrderReturnApplyRich);
    }

    @ApiOperation("修改申请状态")
    @RequestMapping(value = "/update/status/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> updateStatus(@PathVariable Long id, @RequestBody OmsUpdateStatusReq omsUpdateStatusReq) {
        int count = returnApplyService.updateStatus(id, omsUpdateStatusReq);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

}

package com.xqxls.controller;

import com.xqxls.api.CommonResult;
import com.xqxls.domain.member.model.res.SmsCouponHistoryDetailResult;
import com.xqxls.domain.member.model.vo.SmsCouponHistoryVO;
import com.xqxls.domain.member.model.vo.SmsCouponVO;
import com.xqxls.domain.member.service.UmsMemberService;
import com.xqxls.dto.CartPromotionItem;
import com.xqxls.domain.order.service.OmsCartItemService;
import com.xqxls.domain.member.service.UmsMemberCouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户优惠券管理Controller
 * Created by macro on 2018/8/29.
 */
@Controller
@Api(tags = "UmsMemberCouponController", description = "用户优惠券管理")
@RequestMapping("/member/coupon")
public class UmsMemberCouponController {
    @Resource
    private UmsMemberCouponService memberCouponService;
    @Resource
    private OmsCartItemService cartItemService;
    @Resource
    private UmsMemberService memberService;

    @ApiOperation("领取指定优惠券")
    @RequestMapping(value = "/add/{couponId}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> add(@PathVariable Long couponId) {
        memberCouponService.add(couponId);
        return CommonResult.success(null,"领取成功");
    }

    @ApiOperation("获取用户优惠券历史列表")
    @ApiImplicitParam(name = "useStatus", value = "优惠券筛选类型:0->未使用；1->已使用；2->已过期",
            allowableValues = "0,1,2", paramType = "query", dataType = "integer")
    @RequestMapping(value = "/listHistory", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<SmsCouponHistoryVO>> listHistory(@RequestParam(value = "useStatus", required = false) Integer useStatus) {
        List<SmsCouponHistoryVO> couponHistoryList = memberCouponService.listHistory(useStatus);
        return CommonResult.success(couponHistoryList);
    }

    @ApiOperation("获取用户优惠券列表")
    @ApiImplicitParam(name = "useStatus", value = "优惠券筛选类型:0->未使用；1->已使用；2->已过期",
            allowableValues = "0,1,2", paramType = "query", dataType = "integer")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<SmsCouponVO>> list(@RequestParam(value = "useStatus", required = false) Integer useStatus) {
        List<SmsCouponVO> couponList = memberCouponService.list(useStatus);
        return CommonResult.success(couponList);
    }

    @ApiOperation("获取登录会员购物车的相关优惠券")
    @ApiImplicitParam(name = "type", value = "使用可用:0->不可用；1->可用",
            defaultValue = "1", allowableValues = "0,1", paramType = "query", dataType = "integer")
    @RequestMapping(value = "/list/cart/{type}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<SmsCouponHistoryDetailResult>> listCart(@PathVariable Integer type) {
        List<CartPromotionItem> cartPromotionItemList = cartItemService.listPromotionItem(memberService.getCurrentMember().getId(), null);
        List<SmsCouponHistoryDetailResult> couponHistoryList = memberCouponService.listCartResult(cartPromotionItemList, type);
        return CommonResult.success(couponHistoryList);
    }

    @ApiOperation("获取当前商品相关优惠券")
    @RequestMapping(value = "/listByProduct/{productId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<SmsCouponVO>> listByProduct(@PathVariable Long productId) {
        List<SmsCouponVO> couponHistoryList = memberCouponService.listByProduct(productId);
        return CommonResult.success(couponHistoryList);
    }
}

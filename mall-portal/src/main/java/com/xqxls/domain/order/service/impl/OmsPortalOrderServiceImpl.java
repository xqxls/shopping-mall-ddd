package com.xqxls.domain.order.service.impl;

import com.xqxls.convert.member.UmsIntegrationConsumeSettingConvert;
import com.xqxls.domain.member.model.res.SmsCouponHistoryDetailResult;
import com.xqxls.domain.member.model.vo.UmsMemberReceiveAddressVO;
import com.xqxls.domain.member.service.UmsMemberCouponService;
import com.xqxls.domain.member.service.UmsMemberReceiveAddressService;
import com.xqxls.domain.member.service.UmsMemberService;
import com.xqxls.domain.order.model.aggregates.ConfirmOrderRich;
import com.xqxls.domain.order.model.req.OrderReq;
import com.xqxls.domain.order.model.res.CartPromotionItemResult;
import com.xqxls.domain.order.model.res.OmsOrderDetailResult;
import com.xqxls.domain.order.repository.IOmsPortalOrderRepository;
import com.xqxls.domain.order.service.OmsCartItemService;
import com.xqxls.domain.order.service.OmsPortalOrderService;
import com.xqxls.domain.order.service.createOrder.CreateOrderService;
import com.xqxls.dto.CartPromotionItem;
import com.xqxls.model.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 前台订单管理Service
 * Created by macro on 2018/8/30.
 */
@Service
public class OmsPortalOrderServiceImpl implements OmsPortalOrderService {
    @Resource
    private IOmsPortalOrderRepository omsPortalOrderRepository;
    @Resource
    private OmsCartItemService omsCartItemService;
    @Resource
    private UmsMemberService umsMemberService;
    @Resource
    private UmsMemberReceiveAddressService umsMemberReceiveAddressService;
    @Resource
    private UmsMemberCouponService umsMemberCouponService;
    @Resource
    private CreateOrderService createOrderService;


    @Override
    public ConfirmOrderRich generateConfirmOrder(List<Long> cartIds) {

        ConfirmOrderRich result = new ConfirmOrderRich();
        //获取购物车信息
        UmsMember currentMember = umsMemberService.getCurrentMember();
        List<CartPromotionItemResult> cartPromotionItemResultList = omsCartItemService.listPromotion(currentMember.getId(),cartIds);
        result.setCartPromotionItemList(cartPromotionItemResultList);
        //获取用户收货地址列表
        List<UmsMemberReceiveAddressVO> memberReceiveAddressList = umsMemberReceiveAddressService.list();
        result.setMemberReceiveAddressList(memberReceiveAddressList);
        //获取用户可用优惠券列表
        List<CartPromotionItem> cartPromotionItemList = omsCartItemService.listPromotionItem(currentMember.getId(),cartIds);
        List<SmsCouponHistoryDetailResult> couponHistoryDetailResultList = umsMemberCouponService.listCartResult(cartPromotionItemList, 1);
        result.setCouponHistoryDetailList(couponHistoryDetailResultList);
        //获取用户积分
        result.setMemberIntegration(currentMember.getIntegration());
        //获取积分使用规则
        UmsIntegrationConsumeSetting integrationConsumeSetting = omsPortalOrderRepository.selectUmsIntegrationConsumeById(1L);
        result.setIntegrationConsumeSetting(UmsIntegrationConsumeSettingConvert.INSTANCE.convertEntityToVO(integrationConsumeSetting));
        //计算总金额、活动优惠、应付金额
        ConfirmOrderRich.CalcAmount calcAmount = calcCartAmount(cartPromotionItemList);
        result.setCalcAmount(calcAmount);
        return result;
    }

    /**
     * 计算购物车中商品的价格
     */
    private ConfirmOrderRich.CalcAmount calcCartAmount(List<CartPromotionItem> cartPromotionItemList) {
        ConfirmOrderRich.CalcAmount calcAmount = new ConfirmOrderRich.CalcAmount();
        calcAmount.setFreightAmount(new BigDecimal(0));
        BigDecimal totalAmount = new BigDecimal("0");
        BigDecimal promotionAmount = new BigDecimal("0");
        for (CartPromotionItem cartPromotionItem : cartPromotionItemList) {
            totalAmount = totalAmount.add(cartPromotionItem.getPrice().multiply(new BigDecimal(cartPromotionItem.getQuantity())));
            promotionAmount = promotionAmount.add(cartPromotionItem.getReduceAmount().multiply(new BigDecimal(cartPromotionItem.getQuantity())));
        }
        calcAmount.setTotalAmount(totalAmount);
        calcAmount.setPromotionAmount(promotionAmount);
        calcAmount.setPayAmount(totalAmount.subtract(promotionAmount));
        return calcAmount;
    }

    @Override
    public Map<String, Object> generateOrder(OrderReq orderReq) {
        return createOrderService.generateOrder(orderReq);
    }

    @Override
    public Integer paySuccess(Long orderId, Integer payType) {
        return omsPortalOrderRepository.paySuccess(orderId,payType);
    }

    @Override
    public Integer cancelTimeOutOrder() {
        return omsPortalOrderRepository.cancelTimeOutOrder();
    }

    @Override
    public void cancelOrder(Long orderId) {
        omsPortalOrderRepository.cancelOrder(orderId);
    }

    @Override
    public void sendDelayMessageCancelOrder(Long orderId) {
        omsPortalOrderRepository.sendDelayMessageCancelOrder(orderId);
    }

    @Override
    public void confirmReceiveOrder(Long orderId) {
        omsPortalOrderRepository.confirmReceiveOrder(orderId);
    }

    @Override
    public List<OmsOrderDetailResult> list(Integer status, Integer pageNum, Integer pageSize) {
        return omsPortalOrderRepository.list(status,pageNum,pageSize);
    }

    @Override
    public OmsOrderDetailResult detail(Long orderId) {
        return omsPortalOrderRepository.detail(orderId);
    }

    @Override
    public void deleteOrder(Long orderId) {
        omsPortalOrderRepository.deleteOrder(orderId);
    }

    @Override
    public void paySuccessByOrderSn(String orderSn, Integer payType) {
        omsPortalOrderRepository.paySuccessByOrderSn(orderSn,payType);
    }
}

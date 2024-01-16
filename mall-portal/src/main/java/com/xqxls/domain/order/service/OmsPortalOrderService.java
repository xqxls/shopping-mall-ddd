package com.xqxls.domain.order.service;

import com.xqxls.domain.order.model.aggregates.ConfirmOrderRich;
import com.xqxls.domain.order.model.req.OrderReq;
import com.xqxls.domain.order.model.res.OmsOrderDetailResult;
import com.xqxls.model.*;

import java.util.List;
import java.util.Map;

/**
 * 前台订单管理Service
 * Created by macro on 2018/8/30.
 */
public interface OmsPortalOrderService {

    /**
     * 根据用户购物车信息生成确认单信息
     */
    ConfirmOrderRich generateConfirmOrder(List<Long> cartIds);

    /**
     * 根据提交信息生成订单
     */
    Map<String, Object> generateOrder(OrderReq orderReq);

    /**
     * 支付成功后的回调
     */
    Integer paySuccess(Long orderId, Integer payType);

    /**
     * 自动取消超时订单
     */
    Integer cancelTimeOutOrder();

    /**
     * 取消单个超时订单
     */
    void cancelOrder(Long orderId);

    /**
     * 发送延迟消息取消订单
     */
    void sendDelayMessageCancelOrder(Long orderId);

    /**
     * 确认收货
     */
    void confirmReceiveOrder(Long orderId);

    /**
     * 分页获取用户订单
     */
    List<OmsOrderDetailResult> list(Integer status, Integer pageNum, Integer pageSize);

    /**
     * 根据订单ID获取订单详情
     */
    OmsOrderDetailResult detail(Long orderId);

    /**
     * 用户根据订单ID删除订单
     */
    void deleteOrder(Long orderId);

    /**
     * 根据orderSn来实现的支付成功逻辑
     */
    void paySuccessByOrderSn(String orderSn, Integer payType);

}

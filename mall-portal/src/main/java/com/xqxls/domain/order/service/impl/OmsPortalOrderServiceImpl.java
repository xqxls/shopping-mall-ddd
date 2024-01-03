package com.xqxls.domain.order.service.impl;

import com.xqxls.domain.order.model.aggregates.ConfirmOrderRich;
import com.xqxls.domain.order.model.req.OrderReq;
import com.xqxls.domain.order.model.res.OmsOrderDetailResult;
import com.xqxls.domain.order.repository.IOmsPortalOrderRepository;
import com.xqxls.domain.order.service.OmsPortalOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Override
    public ConfirmOrderRich generateConfirmOrder(List<Long> cartIds) {
        return omsPortalOrderRepository.generateConfirmOrder(cartIds);
    }

    @Override
    public Map<String, Object> generateOrder(OrderReq orderReq) {
        return omsPortalOrderRepository.generateOrder(orderReq);
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

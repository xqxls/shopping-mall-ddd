package com.xqxls.oms.service;

import com.xqxls.oms.model.req.OmsMoneyInfoReq;
import com.xqxls.oms.model.req.OmsOrderDeliveryReq;
import com.xqxls.oms.model.req.OmsOrderReq;
import com.xqxls.oms.model.req.OmsReceiverInfoReq;
import com.xqxls.oms.model.res.OmsOrderDetailResult;
import com.xqxls.oms.model.vo.OmsOrderVO;

import java.util.List;

/**
 * 订单管理Service
 * Created by xqxls on 2018/10/11.
 */
public interface OmsOrderService {
    List<OmsOrderVO> list(OmsOrderReq omsOrderReq, Integer pageSize, Integer pageNum);

    /**
     * 批量发货
     */
    int delivery(List<OmsOrderDeliveryReq> deliveryReqList);

    /**
     * 批量关闭订单
     */
    int close(List<Long> ids, String note);

    /**
     * 批量删除订单
     */
    int delete(List<Long> ids);

    /**
     * 获取指定订单详情
     */
    OmsOrderDetailResult detail(Long id);

    /**
     * 修改订单收货人信息
     */
    int updateReceiverInfo(OmsReceiverInfoReq receiverInfoReq);

    /**
     * 修改订单费用信息
     */
    int updateMoneyInfo(OmsMoneyInfoReq moneyInfoReq);

    /**
     * 修改订单备注
     */
    int updateNote(Long id, String note, Integer status);
}

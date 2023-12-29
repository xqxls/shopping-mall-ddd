package com.xqxls.oms.repository;

import com.xqxls.oms.model.req.OmsMoneyInfoReq;
import com.xqxls.oms.model.req.OmsOrderDeliveryReq;
import com.xqxls.oms.model.req.OmsOrderReq;
import com.xqxls.oms.model.req.OmsReceiverInfoReq;
import com.xqxls.oms.model.res.OmsOrderDetailResult;
import com.xqxls.oms.model.vo.OmsOrderVO;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/29 11:22
 */
public interface IOmsOrderRepository {

    /**
     * 订单查询
     */
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

package com.xqxls.domain.order.service.createOrder;

import com.xqxls.domain.order.model.req.OrderReq;

import java.util.Map;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/16 21:05
 */
public interface ICreateOrderService {


    /**
     * 根据提交信息生成订单
     */
    Map<String, Object> generateOrder(OrderReq orderReq);
}

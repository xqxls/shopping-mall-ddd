package com.xqxls.domain.pay.repository;

import com.xqxls.domain.pay.model.req.AliPayReq;

import java.util.Map;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/2 21:58
 */
public interface IAlipayRepository {

    /**
     * 根据提交参数生成电脑支付页面
     */
    String pay(AliPayReq aliPayReq);

    /**
     * 支付宝异步回调处理
     */
    String notify(Map<String, String> params);

    /**
     * 查询支付宝交易状态
     * @param outTradeNo 商户订单编号
     * @param tradeNo 支付宝交易编号
     * @return 支付宝交易状态
     */
    String query(String outTradeNo, String tradeNo);

    /**
     * 根据提交参数生成手机支付页面
     */
    String webPay(AliPayReq aliPayReq);
}

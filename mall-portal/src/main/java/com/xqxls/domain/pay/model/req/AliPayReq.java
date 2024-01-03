package com.xqxls.domain.pay.model.req;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/2 21:58
 */
@Data
public class AliPayReq {

    /**
     * 商户订单号，商家自定义，保持唯一性
     */
    private String outTradeNo;
    /**
     * 商品的标题/交易标题/订单标题/订单关键字等
     */
    private String subject;
    /**
     * 订单总金额，单位为元，精确到小数点后两位
     */
    private BigDecimal totalAmount;
}

package com.xqxls.domain.pay.service.impl;

import com.xqxls.domain.pay.model.req.AliPayReq;
import com.xqxls.domain.pay.repository.IAlipayRepository;
import com.xqxls.domain.pay.service.AlipayService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @auther macrozheng
 * @description 支付宝支付Service实现类
 * @date 2023/9/8
 */
@Service
public class AlipayServiceImpl implements AlipayService {
    @Resource
    private IAlipayRepository alipayRepository;

    @Override
    public String pay(AliPayReq aliPayReq) {
        return alipayRepository.pay(aliPayReq);
    }

    @Override
    public String notify(Map<String, String> params) {
        return alipayRepository.notify(params);
    }

    @Override
    public String query(String outTradeNo, String tradeNo) {
        return alipayRepository.query(outTradeNo,tradeNo);
    }

    @Override
    public String webPay(AliPayReq aliPayReq) {
        return alipayRepository.webPay(aliPayReq);
    }
}

package com.xqxls.sms.repository;

import com.xqxls.sms.model.vo.SmsCouponHistoryVO;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/29 22:48
 */
public interface ISmsCouponHistoryRepository {

    /**
     * 分页查询优惠券领取记录
     * @param couponId 优惠券id
     * @param useStatus 使用状态
     * @param orderSn 使用订单号码
     */
    List<SmsCouponHistoryVO> list(Long couponId, Integer useStatus, String orderSn, Integer pageSize, Integer pageNum);
}


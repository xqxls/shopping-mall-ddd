package com.xqxls.sms.service;

import com.xqxls.sms.model.aggregates.SmsCouponRich;
import com.xqxls.sms.model.vo.SmsCouponVO;

import java.util.List;

/**
 * 优惠券管理Service
 * Created by xqxls on 2018/8/28.
 */
public interface SmsCouponService {

    /**
     * 添加优惠券
     */
    int create(SmsCouponRich couponRich);

    /**
     * 根据优惠券id删除优惠券
     */
    int delete(Long id);

    /**
     * 根据优惠券id更新优惠券信息
     */
    int update(Long id, SmsCouponRich couponRich);

    /**
     * 分页获取优惠券列表
     */
    List<SmsCouponVO> list(String name, Integer type, Integer pageSize, Integer pageNum);

    /**
     * 获取优惠券详情
     * @param id 优惠券表id
     */
    SmsCouponRich getItem(Long id);
}

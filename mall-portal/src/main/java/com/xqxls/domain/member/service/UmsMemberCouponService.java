package com.xqxls.domain.member.service;

import com.xqxls.domain.member.model.aggregates.CartPromotionItemRich;
import com.xqxls.domain.member.model.res.SmsCouponHistoryDetailResult;
import com.xqxls.domain.member.model.vo.SmsCouponHistoryVO;
import com.xqxls.domain.member.model.vo.SmsCouponVO;
import com.xqxls.dto.CartPromotionItem;

import java.util.List;

/**
 * 用户优惠券管理Service
 * Created by macro on 2018/8/29.
 */
public interface UmsMemberCouponService {
    /**
     * 会员添加优惠券
     */
    void add(Long couponId);

    /**
     * 获取优惠券历史列表
     */
    List<SmsCouponHistoryVO> listHistory(Integer useStatus);

    /**
     * 根据购物车信息获取可用优惠券
     */
    List<SmsCouponHistoryDetailResult> listCart(List<CartPromotionItemRich> cartPromotionItemRichList, Integer type);

    /**
     * 根据购物车信息获取可用优惠券
     */
    List<SmsCouponHistoryDetailResult> listCartResult(List<CartPromotionItem> cartPromotionItemList, Integer type);

    /**
     * 获取当前商品相关优惠券
     */
    List<SmsCouponVO> listByProduct(Long productId);

    /**
     * 获取用户优惠券列表
     */
    List<SmsCouponVO> list(Integer useStatus);
}

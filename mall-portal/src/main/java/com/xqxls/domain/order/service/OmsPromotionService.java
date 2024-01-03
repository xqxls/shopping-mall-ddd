package com.xqxls.domain.order.service;

import com.xqxls.domain.order.model.res.CartPromotionItemResult;
import com.xqxls.domain.order.model.vo.OmsCartItemVO;

import java.util.List;

/**
 * Created by macro on 2018/8/27.
 * 促销管理Service
 */
public interface OmsPromotionService {
    /**
     * 计算购物车中的促销活动信息
     * @param cartItemVOList 购物车
     */
    List<CartPromotionItemResult> calcCartPromotion(List<OmsCartItemVO> cartItemVOList);
}

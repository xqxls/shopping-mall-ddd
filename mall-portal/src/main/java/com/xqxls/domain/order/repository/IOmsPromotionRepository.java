package com.xqxls.domain.order.repository;

import com.xqxls.domain.order.model.res.CartPromotionItemResult;
import com.xqxls.domain.order.model.vo.OmsCartItemVO;
import com.xqxls.dto.CartPromotionItem;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/3 9:15
 */
public interface IOmsPromotionRepository {

    /**
     * 计算购物车中的促销活动信息
     * @param cartItemVOList 购物车
     */
    List<CartPromotionItemResult> calcCartPromotion(List<OmsCartItemVO> cartItemVOList);

    /**
     * 计算购物车中的促销活动信息
     * @param cartItemVOList 购物车
     */
    List<CartPromotionItem> calcCartPromotionItem(List<OmsCartItemVO> cartItemVOList);
}


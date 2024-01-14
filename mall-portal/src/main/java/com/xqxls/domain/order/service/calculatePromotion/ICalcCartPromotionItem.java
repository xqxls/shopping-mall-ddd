package com.xqxls.domain.order.service.calculatePromotion;

import com.xqxls.domain.order.model.vo.OmsCartItemVO;
import com.xqxls.dto.CartPromotionItem;
import com.xqxls.dto.PromotionProduct;
import com.xqxls.model.OmsCartItem;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/14 18:53
 */
public interface ICalcCartPromotionItem {

    /**
     * 策略处理购物车中的促销活动信息
     * @param cartPromotionItemList 促销活动信息
     * @param itemList 按商品分组的购物车列表
     * @param promotionProduct 促销商品
     * @param cartItemVOList 购物车列表
     */
    void handleCartItemList(List<CartPromotionItem> cartPromotionItemList, List<OmsCartItem> itemList, PromotionProduct promotionProduct, List<OmsCartItemVO> cartItemVOList);
}

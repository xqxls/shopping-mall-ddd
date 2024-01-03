package com.xqxls.domain.order.service;

import com.xqxls.domain.order.model.res.CartProductResult;
import com.xqxls.domain.order.model.res.CartPromotionItemResult;
import com.xqxls.domain.order.model.vo.OmsCartItemVO;
import com.xqxls.dto.CartPromotionItem;
import com.xqxls.model.OmsCartItem;

import java.util.List;

/**
 * 购物车管理Service
 * Created by macro on 2018/8/2.
 */
public interface OmsCartItemService {
    /**
     * 查询购物车中是否包含该商品，有增加数量，无添加到购物车
     */
    int add(OmsCartItemVO cartItemVO);


    OmsCartItem getCartItem(OmsCartItem cartItem);

    /**
     * 根据会员编号获取购物车列表
     */
    List<OmsCartItemVO> list(Long memberId);

    /**
     * 获取包含促销活动信息的购物车列表
     */
    List<CartPromotionItemResult> listPromotion(Long memberId, List<Long> cartIds);

    /**
     * 获取包含促销活动信息的购物车列表
     */
    List<CartPromotionItem> listPromotionItem(Long memberId, List<Long> cartIds);

    /**
     * 修改某个购物车商品的数量
     */
    int updateQuantity(Long id, Long memberId, Integer quantity);

    /**
     * 批量删除购物车中的商品
     */
    int delete(Long memberId,List<Long> ids);

    /**
     *获取购物车中用于选择商品规格的商品信息
     */
    CartProductResult getCartProduct(Long productId);

    /**
     * 修改购物车中商品的规格
     */
    int updateAttr(OmsCartItemVO cartItemVO);

    /**
     * 清空购物车
     */
    int clear(Long memberId);
}

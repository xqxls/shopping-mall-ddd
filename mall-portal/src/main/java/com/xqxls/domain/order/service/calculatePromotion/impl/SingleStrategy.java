package com.xqxls.domain.order.service.calculatePromotion.impl;

import com.xqxls.domain.order.model.vo.OmsCartItemVO;
import com.xqxls.domain.order.service.calculatePromotion.AbstractCalcCartPromotionItem;
import com.xqxls.dto.CartPromotionItem;
import com.xqxls.dto.PromotionProduct;
import com.xqxls.model.OmsCartItem;
import com.xqxls.model.PmsSkuStock;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/14 18:56
 */
@Service("singleStrategy")
public class SingleStrategy extends AbstractCalcCartPromotionItem {

    @Override
    public void handleCartItemList(List<CartPromotionItem> cartPromotionItemList, List<OmsCartItem> itemList, PromotionProduct promotionProduct, List<OmsCartItemVO> cartItemVOList) {
        //单品促销
        for (OmsCartItem item : itemList) {
            CartPromotionItem cartPromotionItem = new CartPromotionItem();
            BeanUtils.copyProperties(item,cartPromotionItem);
            cartPromotionItem.setPromotionMessage("单品促销");
            //商品原价-促销价
            PmsSkuStock skuStock = super.getOriginalPrice(promotionProduct, item.getProductSkuId());
            BigDecimal originalPrice = skuStock.getPrice();
            //单品促销使用原价
            cartPromotionItem.setPrice(originalPrice);
            cartPromotionItem.setReduceAmount(originalPrice.subtract(skuStock.getPromotionPrice()));
            cartPromotionItem.setRealStock(skuStock.getStock()-skuStock.getLockStock());
            cartPromotionItem.setIntegration(promotionProduct.getGiftPoint());
            cartPromotionItem.setGrowth(promotionProduct.getGiftGrowth());
            cartPromotionItemList.add(cartPromotionItem);
        }
    }

}

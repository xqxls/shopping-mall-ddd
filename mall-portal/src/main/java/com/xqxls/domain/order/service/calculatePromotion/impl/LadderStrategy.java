package com.xqxls.domain.order.service.calculatePromotion.impl;

import com.xqxls.domain.order.model.vo.OmsCartItemVO;
import com.xqxls.domain.order.service.calculatePromotion.AbstractCalcCartPromotionItem;
import com.xqxls.dto.CartPromotionItem;
import com.xqxls.dto.PromotionProduct;
import com.xqxls.model.OmsCartItem;
import com.xqxls.model.PmsProductLadder;
import com.xqxls.model.PmsSkuStock;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/14 19:22
 */
@Service("ladderStrategy")
public class LadderStrategy extends AbstractCalcCartPromotionItem {

    @Override
    public void handleCartItemList(List<CartPromotionItem> cartPromotionItemList, List<OmsCartItem> itemList, PromotionProduct promotionProduct, List<OmsCartItemVO> cartItemVOList) {
        //打折优惠
        int count = getCartItemCount(itemList);
        PmsProductLadder ladder = getProductLadder(count, promotionProduct.getProductLadderList());
        if(ladder!=null){
            for (OmsCartItem item : itemList) {
                CartPromotionItem cartPromotionItem = new CartPromotionItem();
                BeanUtils.copyProperties(item,cartPromotionItem);
                String message = getLadderPromotionMessage(ladder);
                cartPromotionItem.setPromotionMessage(message);
                //商品原价-折扣*商品原价
                PmsSkuStock skuStock = getOriginalPrice(promotionProduct,item.getProductSkuId());
                BigDecimal originalPrice = skuStock.getPrice();
                BigDecimal reduceAmount = originalPrice.subtract(ladder.getDiscount().multiply(originalPrice));
                cartPromotionItem.setReduceAmount(reduceAmount);
                cartPromotionItem.setRealStock(skuStock.getStock()-skuStock.getLockStock());
                cartPromotionItem.setIntegration(promotionProduct.getGiftPoint());
                cartPromotionItem.setGrowth(promotionProduct.getGiftGrowth());
                cartPromotionItemList.add(cartPromotionItem);
            }
        }
        else{
            super.handleNoReduce(cartPromotionItemList,itemList,promotionProduct);
        }
    }

    /**
     * 获取购物车中指定商品的数量
     */
    private int getCartItemCount(List<OmsCartItem> itemList) {
        int count = 0;
        for (OmsCartItem item : itemList) {
            count += item.getQuantity();
        }
        return count;
    }

    /**
     * 根据购买商品数量获取满足条件的打折优惠策略
     */
    private PmsProductLadder getProductLadder(int count, List<PmsProductLadder> productLadderList) {
        //按数量从大到小排序
        productLadderList.sort((o1, o2) -> o2.getCount() - o1.getCount());
        for (PmsProductLadder productLadder : productLadderList) {
            if (count >= productLadder.getCount()) {
                return productLadder;
            }
        }
        return null;
    }

    /**
     * 获取打折优惠的促销信息
     */
    private String getLadderPromotionMessage(PmsProductLadder ladder) {
        return "打折优惠：" +
                "满" +
                ladder.getCount() +
                "件，" +
                "打" +
                ladder.getDiscount().multiply(new BigDecimal(10)) +
                "折";
    }
}



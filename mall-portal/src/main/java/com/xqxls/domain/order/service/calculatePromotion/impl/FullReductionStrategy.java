package com.xqxls.domain.order.service.calculatePromotion.impl;

import com.xqxls.convert.order.OmsPromotionConvert;
import com.xqxls.domain.order.model.vo.OmsCartItemVO;
import com.xqxls.domain.order.service.calculatePromotion.AbstractCalcCartPromotionItem;
import com.xqxls.dto.CartPromotionItem;
import com.xqxls.dto.PromotionProduct;
import com.xqxls.model.OmsCartItem;
import com.xqxls.model.PmsProductFullReduction;
import com.xqxls.model.PmsSkuStock;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/14 19:23
 */
@Service("fullReductionStrategy")
public class FullReductionStrategy extends AbstractCalcCartPromotionItem {

    @Override
    public void handleCartItemList(List<CartPromotionItem> cartPromotionItemList, List<OmsCartItem> itemList, PromotionProduct promotionProduct, List<OmsCartItemVO> cartItemVOList) {
        List<OmsCartItem> cartItemList = OmsPromotionConvert.INSTANCE.omsCartItemVOToEntityList(cartItemVOList);
        //满减
        BigDecimal totalAmount= getCartItemAmount(itemList,super.getPromotionProductList(cartItemList));
        PmsProductFullReduction fullReduction = getProductFullReduction(totalAmount,promotionProduct.getProductFullReductionList());
        if(fullReduction!=null){
            for (OmsCartItem item : itemList) {
                CartPromotionItem cartPromotionItem = new CartPromotionItem();
                BeanUtils.copyProperties(item,cartPromotionItem);
                String message = getFullReductionPromotionMessage(fullReduction);
                cartPromotionItem.setPromotionMessage(message);
                //(商品原价/总价)*满减金额
                PmsSkuStock skuStock= getOriginalPrice(promotionProduct, item.getProductSkuId());
                BigDecimal originalPrice = skuStock.getPrice();
                BigDecimal reduceAmount = originalPrice.divide(totalAmount, RoundingMode.HALF_EVEN).multiply(fullReduction.getReducePrice());
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
     * 获取购物车中指定商品的总价
     */
    private BigDecimal getCartItemAmount(List<OmsCartItem> itemList, List<PromotionProduct> promotionProductList) {
        BigDecimal amount = new BigDecimal(0);
        for (OmsCartItem item : itemList) {
            //计算出商品原价
            PromotionProduct promotionProduct = super.getPromotionProductById(item.getProductId(), promotionProductList);
            PmsSkuStock skuStock = getOriginalPrice(promotionProduct,item.getProductSkuId());
            amount = amount.add(skuStock.getPrice().multiply(new BigDecimal(item.getQuantity())));
        }
        return amount;
    }

    private PmsProductFullReduction getProductFullReduction(BigDecimal totalAmount,List<PmsProductFullReduction> fullReductionList) {
        //按条件从高到低排序
        fullReductionList.sort((o1, o2) -> o2.getFullPrice().subtract(o1.getFullPrice()).intValue());
        for(PmsProductFullReduction fullReduction:fullReductionList){
            if(totalAmount.subtract(fullReduction.getFullPrice()).intValue()>=0){
                return fullReduction;
            }
        }
        return null;
    }

    /**
     * 获取满减促销消息
     */
    private String getFullReductionPromotionMessage(PmsProductFullReduction fullReduction) {
        return "满减优惠：" +
                "满" +
                fullReduction.getFullPrice() +
                "元，" +
                "减" +
                fullReduction.getReducePrice() +
                "元";
    }
}


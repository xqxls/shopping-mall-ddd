package com.xqxls.domain.order.service.calculatePromotion.impl;

import com.xqxls.domain.order.model.vo.OmsCartItemVO;
import com.xqxls.domain.order.service.calculatePromotion.AbstractCalcCartPromotionItem;
import com.xqxls.dto.CartPromotionItem;
import com.xqxls.dto.PromotionProduct;
import com.xqxls.model.OmsCartItem;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/14 20:12
 */
@Service("noReduceStrategy")
public class NoReduceStrategy extends AbstractCalcCartPromotionItem {

    @Override
    public void handleCartItemList(List<CartPromotionItem> cartPromotionItemList, List<OmsCartItem> itemList, PromotionProduct promotionProduct, List<OmsCartItemVO> cartItemVOList) {
        super.handleNoReduce(cartPromotionItemList,itemList,promotionProduct);
    }


}

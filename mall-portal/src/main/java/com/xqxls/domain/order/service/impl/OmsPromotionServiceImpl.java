package com.xqxls.domain.order.service.impl;

import com.xqxls.convert.order.OmsPromotionConvert;
import com.xqxls.domain.order.model.res.CartPromotionItemResult;
import com.xqxls.domain.order.model.vo.OmsCartItemVO;
import com.xqxls.domain.order.service.OmsPromotionService;
import com.xqxls.domain.order.service.calculatePromotion.CalcCartPromotionItemService;
import com.xqxls.dto.CartPromotionItem;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by macro on 2018/8/27.
 * 促销管理Service实现类
 */
@Service
public class OmsPromotionServiceImpl implements OmsPromotionService {

    @Resource
    private CalcCartPromotionItemService calcCartPromotionItemService;

    @Override
    public List<CartPromotionItemResult> calcCartPromotion(List<OmsCartItemVO> cartItemVOList) {
        return OmsPromotionConvert.INSTANCE.cartPromotionItemEntityToResultList(this.calcCartPromotionItem(cartItemVOList));
    }

    @Override
    public List<CartPromotionItem> calcCartPromotionItem(List<OmsCartItemVO> cartItemVOList) {

        return calcCartPromotionItemService.calcCartPromotionItem(cartItemVOList);
    }
}

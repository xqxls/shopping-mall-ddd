package com.xqxls.domain.member.model.aggregates;

import com.xqxls.domain.order.model.vo.OmsCartItemVO;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/2 16:57
 */
@Data
public class CartPromotionItemRich {

    private OmsCartItemVO omsCartItem;

    //促销活动信息
    private String promotionMessage;
    //促销活动减去的金额，针对每个商品
    private BigDecimal reduceAmount;
    //商品的真实库存（剩余库存-锁定库存）
    private Integer realStock;
    //购买商品赠送积分
    private Integer integration;
    //购买商品赠送成长值
    private Integer growth;
}

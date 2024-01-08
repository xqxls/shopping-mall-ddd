package com.xqxls.domain.product.model.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/4 19:09
 */
@Setter
@Getter
public class FlashPromotionProductVO extends PmsProductVO {
    private BigDecimal flashPromotionPrice;
    private Integer flashPromotionCount;
    private Integer flashPromotionLimit;
}

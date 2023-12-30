package com.xqxls.sms.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/30 16:29
 */
@Data
public class SmsFlashPromotionProductRelationVO {

    @ApiModelProperty(value = "编号")
    private Long id;

    private Long flashPromotionId;

    @ApiModelProperty(value = "编号")
    private Long flashPromotionSessionId;

    private Long productId;

    @ApiModelProperty(value = "限时购价格")
    private BigDecimal flashPromotionPrice;

    @ApiModelProperty(value = "限时购数量")
    private Integer flashPromotionCount;

    @ApiModelProperty(value = "每人限购数量")
    private Integer flashPromotionLimit;

    @ApiModelProperty(value = "排序")
    private Integer sort;
}

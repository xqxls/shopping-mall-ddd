package com.xqxls.domain.member.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/2 16:55
 */
@Data
public class SmsCouponProductRelationVO {
    private Long id;

    private Long couponId;

    private Long productId;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "商品编码")
    private String productSn;
}

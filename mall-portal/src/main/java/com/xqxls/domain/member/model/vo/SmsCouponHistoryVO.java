package com.xqxls.domain.member.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/2 16:52
 */
@Data
public class SmsCouponHistoryVO {

    private Long id;

    private Long couponId;

    private Long memberId;

    private String couponCode;

    @ApiModelProperty(value = "领取人昵称")
    private String memberNickname;

    @ApiModelProperty(value = "获取类型：0->后台赠送；1->主动获取")
    private Integer getType;

    private Date createTime;

    @ApiModelProperty(value = "使用状态：0->未使用；1->已使用；2->已过期")
    private Integer useStatus;

    @ApiModelProperty(value = "使用时间")
    private Date useTime;

    @ApiModelProperty(value = "订单编号")
    private Long orderId;

    @ApiModelProperty(value = "订单号码")
    private String orderSn;
}

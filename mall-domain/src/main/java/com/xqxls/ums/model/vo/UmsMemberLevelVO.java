package com.xqxls.ums.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/27 17:02
 */
@Data
public class UmsMemberLevelVO {

    private Long id;

    private String name;

    private Integer growthPoint;

    @ApiModelProperty(value = "是否为默认等级：0->不是；1->是")
    private Integer defaultStatus;

    @ApiModelProperty(value = "免运费标准")
    private BigDecimal freeFreightPoint;

    @ApiModelProperty(value = "每次评价获取的成长值")
    private Integer commentGrowthPoint;

    @ApiModelProperty(value = "是否有免邮特权")
    private Integer priviledgeFreeFreight;

    @ApiModelProperty(value = "是否有签到特权")
    private Integer priviledgeSignIn;

    @ApiModelProperty(value = "是否有评论获奖励特权")
    private Integer priviledgeComment;

    @ApiModelProperty(value = "是否有专享活动特权")
    private Integer priviledgePromotion;

    @ApiModelProperty(value = "是否有会员价格特权")
    private Integer priviledgeMemberPrice;

    @ApiModelProperty(value = "是否有生日特权")
    private Integer priviledgeBirthday;

    private String note;
}

package com.xqxls.sms.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/30 17:10
 */
@Data
public class SmsFlashPromotionSessionVO {

    @ApiModelProperty(value = "编号")
    private Long id;

    @ApiModelProperty(value = "场次名称")
    private String name;

    @ApiModelProperty(value = "每日开始时间")
    private Date startTime;

    @ApiModelProperty(value = "每日结束时间")
    private Date endTime;

    @ApiModelProperty(value = "启用状态：0->不启用；1->启用")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}

package com.xqxls.domain.member.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/2 15:24
 */
@Data
public class UmsMemberReceiveAddressVO {
    private Long id;

    private Long memberId;

    @ApiModelProperty(value = "收货人名称")
    private String name;

    private String phoneNumber;

    @ApiModelProperty(value = "是否为默认")
    private Integer defaultStatus;

    @ApiModelProperty(value = "邮政编码")
    private String postCode;

    @ApiModelProperty(value = "省份/直辖市")
    private String province;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "区")
    private String region;

    @ApiModelProperty(value = "详细地址(街道)")
    private String detailAddress;
}

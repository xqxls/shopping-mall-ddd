package com.xqxls.pms.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/28 16:07
 */
@Data
public class PmsMemberPriceVO {

    private Long id;

    private Long productId;

    private Long memberLevelId;

    @ApiModelProperty(value = "会员价格")
    private BigDecimal memberPrice;

    private String memberLevelName;
}

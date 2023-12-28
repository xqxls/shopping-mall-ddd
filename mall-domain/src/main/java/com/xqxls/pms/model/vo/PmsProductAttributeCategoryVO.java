package com.xqxls.pms.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/28 10:42
 */
@Data
public class PmsProductAttributeCategoryVO {

    private Long id;

    private String name;

    @ApiModelProperty(value = "属性数量")
    private Integer attributeCount;

    @ApiModelProperty(value = "参数数量")
    private Integer paramCount;
}

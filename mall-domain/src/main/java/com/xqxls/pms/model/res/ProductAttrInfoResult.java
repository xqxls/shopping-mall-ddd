package com.xqxls.pms.model.res;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/28 11:30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProductAttrInfoResult {

    @ApiModelProperty("商品属性ID")
    private Long attributeId;
    @ApiModelProperty("商品属性分类ID")
    private Long attributeCategoryId;
}

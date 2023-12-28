package com.xqxls.pms.model.res;

import com.xqxls.pms.model.aggregates.PmsProductRich;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/28 16:24
 */
public class PmsProductUpdateResult extends PmsProductRich {

    @ApiModelProperty("商品所选分类的父id")
    private Long cateParentId;
}

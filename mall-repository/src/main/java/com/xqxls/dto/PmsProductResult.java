package com.xqxls.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 查询单个产品进行修改时返回的结果
 * Created by xqxls on 2018/4/26.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class PmsProductResult extends PmsProductParam {

    @ApiModelProperty("商品所选分类的父id")
    private Long cateParentId;

}

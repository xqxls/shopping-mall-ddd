package com.xqxls.dto;

import com.xqxls.model.PmsProductAttribute;
import com.xqxls.model.PmsProductAttributeCategory;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 包含有分类下属性的dto
 * Created by xqxls on 2018/5/24.
 */
@Getter
@Setter
public class PmsProductAttributeCategoryItem extends PmsProductAttributeCategory {


    @ApiModelProperty(value = "商品属性列表")
    private List<PmsProductAttribute> productAttributeList;
}

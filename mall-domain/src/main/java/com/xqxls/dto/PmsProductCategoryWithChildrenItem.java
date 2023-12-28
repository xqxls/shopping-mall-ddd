package com.xqxls.dto;

import com.xqxls.model.PmsProductCategory;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by xqxls on 2018/5/25.
 */
@Getter
@Setter
public class PmsProductCategoryWithChildrenItem extends PmsProductCategory {

    @ApiModelProperty("子级分类")
    private List<PmsProductCategory> children;
}

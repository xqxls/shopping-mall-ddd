package com.xqxls.pms.model.res;

import com.xqxls.pms.model.vo.PmsProductAttributeCategoryVO;
import com.xqxls.pms.model.vo.PmsProductAttributeVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/28 10:49
 */
@Getter
@Setter
public class PmsProductAttributeCategoryItemResult extends PmsProductAttributeCategoryVO {

    @ApiModelProperty(value = "商品属性列表")
    private List<PmsProductAttributeVO> productAttributeList;
}

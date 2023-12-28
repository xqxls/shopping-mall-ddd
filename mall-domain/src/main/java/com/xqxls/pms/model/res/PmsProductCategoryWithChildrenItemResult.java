package com.xqxls.pms.model.res;

import com.xqxls.pms.model.vo.PmsProductCategoryVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/28 14:43
 */
@Getter
@Setter
public class PmsProductCategoryWithChildrenItemResult extends PmsProductCategoryVO {

    @ApiModelProperty("子级分类")
    private List<PmsProductCategoryVO> children;
}

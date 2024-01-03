package com.xqxls.domain.product.model.res;

import com.xqxls.domain.product.model.vo.PmsProductCategoryVO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/2 20:14
 */
@Getter
@Setter
public class PmsProductCategoryNodeResult extends PmsProductCategoryVO {

    private List<PmsProductCategoryNodeResult> children;
}

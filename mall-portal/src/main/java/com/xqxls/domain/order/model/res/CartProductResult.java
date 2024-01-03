package com.xqxls.domain.order.model.res;

import com.xqxls.domain.product.model.vo.PmsProductAttributeVO;
import com.xqxls.domain.product.model.vo.PmsProductVO;
import com.xqxls.domain.product.model.vo.PmsSkuStockVO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/3 21:07
 */
@Getter
@Setter
public class CartProductResult extends PmsProductVO {

    private List<PmsProductAttributeVO> productAttributeVOList;

    private List<PmsSkuStockVO> skuStockVOList;
}

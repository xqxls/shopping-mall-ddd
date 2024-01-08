package com.xqxls.dto;

import com.xqxls.model.PmsProduct;
import com.xqxls.model.PmsProductAttribute;
import com.xqxls.model.PmsSkuStock;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 购物车中选择规格的商品信息
 * Created by macro on 2018/8/2.
 */
@Getter
@Setter
public class CartProduct extends PmsProduct {

    private List<PmsProductAttribute> productAttributeList;
    private List<PmsSkuStock> skuStockList;

}

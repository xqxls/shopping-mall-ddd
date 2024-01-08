package com.xqxls.dto;

import com.xqxls.model.PmsProduct;
import com.xqxls.model.PmsProductFullReduction;
import com.xqxls.model.PmsProductLadder;
import com.xqxls.model.PmsSkuStock;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by macro on 2018/8/27.
 * 商品的促销信息，包括sku、打折优惠、满减优惠
 */
@Getter
@Setter
public class PromotionProduct extends PmsProduct {
    //商品库存信息
    private List<PmsSkuStock> skuStockList;
    //商品打折信息
    private List<PmsProductLadder> productLadderList;
    //商品满减信息
    private List<PmsProductFullReduction> productFullReductionList;

}

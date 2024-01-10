package com.xqxls.domain.product.model.aggregates;

import com.xqxls.domain.member.model.vo.SmsCouponVO;
import com.xqxls.domain.product.model.vo.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/2 20:17
 */
@Getter
@Setter
public class PmsPortalProductDetailRich {

    @ApiModelProperty("商品信息")
    private PmsProductVO product;
    @ApiModelProperty("商品品牌")
    private PmsBrandVO brand;
    @ApiModelProperty("商品属性与参数")
    private List<PmsProductAttributeVO> productAttributeList;
    @ApiModelProperty("手动录入的商品属性与参数值")
    private List<PmsProductAttributeValueVO> productAttributeValueList;
    @ApiModelProperty("商品的sku库存信息")
    private List<PmsSkuStockVO> skuStockList;
    @ApiModelProperty("商品阶梯价格设置")
    private List<PmsProductLadderVO> productLadderList;
    @ApiModelProperty("商品满减价格设置")
    private List<PmsProductFullReductionVO> productFullReductionList;
    @ApiModelProperty("商品可用优惠券")
    private List<SmsCouponVO> couponList;
}

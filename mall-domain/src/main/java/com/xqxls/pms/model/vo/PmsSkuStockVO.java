package com.xqxls.pms.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/28 15:24
 */
@Data
public class PmsSkuStockVO {

    private Long id;

    private Long productId;

    @ApiModelProperty(value = "sku编码")
    private String skuCode;

    private BigDecimal price;

    @ApiModelProperty(value = "库存")
    private Integer stock;

    @ApiModelProperty(value = "预警库存")
    private Integer lowStock;

    @ApiModelProperty(value = "展示图片")
    private String pic;

    @ApiModelProperty(value = "销量")
    private Integer sale;

    @ApiModelProperty(value = "单品促销价格")
    private BigDecimal promotionPrice;

    @ApiModelProperty(value = "锁定库存")
    private Integer lockStock;

    @ApiModelProperty(value = "商品销售属性，json格式")
    private String spData;
}

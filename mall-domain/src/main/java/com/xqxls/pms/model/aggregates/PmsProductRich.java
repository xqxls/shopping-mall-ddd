package com.xqxls.pms.model.aggregates;

import com.xqxls.model.*;
import com.xqxls.pms.model.vo.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/28 16:03
 */
@Data
public class PmsProductRich {

    @ApiModelProperty("商品设置")
    private PmsProductVO pmsProductVO;
    @ApiModelProperty("商品阶梯价格设置")
    private List<PmsProductLadderVO> productLadderList;
    @ApiModelProperty("商品满减价格设置")
    private List<PmsProductFullReductionVO> productFullReductionList;
    @ApiModelProperty("商品会员价格设置")
    private List<PmsMemberPriceVO> memberPriceList;
    @ApiModelProperty("商品的sku库存信息")
    private List<PmsSkuStockVO> skuStockList;
    @ApiModelProperty("商品参数及自定义规格属性")
    private List<PmsProductAttributeValueVO> productAttributeValueList;
    @ApiModelProperty("专题和商品关系")
    private List<CmsSubjectProductRelationVO> subjectProductRelationList;
    @ApiModelProperty("优选专区和商品的关系")
    private List<CmsPrefrenceAreaProductRelationVO> prefrenceAreaProductRelationList;
}

package com.xqxls.sms.model.aggregates;

import com.xqxls.sms.model.vo.SmsCouponProductCategoryRelationVO;
import com.xqxls.sms.model.vo.SmsCouponProductRelationVO;
import com.xqxls.sms.model.vo.SmsCouponVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/30 15:52
 */
@Data
public class SmsCouponRich {

    @ApiModelProperty("优惠券")
    private SmsCouponVO smsCouponVO;

    @ApiModelProperty("优惠券绑定的商品")
    private List<SmsCouponProductRelationVO> productRelationVOList;

    @ApiModelProperty("优惠券绑定的商品分类")
    private List<SmsCouponProductCategoryRelationVO> productCategoryRelationVOList;
}

package com.xqxls.domain.member.model.res;

import com.xqxls.domain.member.model.vo.SmsCouponHistoryVO;
import com.xqxls.domain.member.model.vo.SmsCouponProductCategoryRelationVO;
import com.xqxls.domain.member.model.vo.SmsCouponProductRelationVO;
import com.xqxls.domain.member.model.vo.SmsCouponVO;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/2 16:54
 */
@Data
public class SmsCouponHistoryDetailResult extends SmsCouponHistoryVO {
    //相关优惠券信息
    private SmsCouponVO coupon;
    //优惠券关联商品
    private List<SmsCouponProductRelationVO> productRelationList;
    //优惠券关联商品分类
    private List<SmsCouponProductCategoryRelationVO> categoryRelationList;
}

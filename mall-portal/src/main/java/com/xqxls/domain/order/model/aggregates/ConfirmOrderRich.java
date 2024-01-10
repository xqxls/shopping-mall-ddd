package com.xqxls.domain.order.model.aggregates;

import com.xqxls.domain.member.model.res.SmsCouponHistoryDetailResult;
import com.xqxls.domain.member.model.vo.UmsIntegrationConsumeSettingVO;
import com.xqxls.domain.member.model.vo.UmsMemberReceiveAddressVO;
import com.xqxls.domain.order.model.res.CartPromotionItemResult;
import com.xqxls.domain.order.model.vo.CartPromotionItemVO;
import com.xqxls.dto.CartPromotionItem;
import com.xqxls.dto.SmsCouponHistoryDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/3 21:29
 */
@Data
public class ConfirmOrderRich {

    //包含优惠信息的购物车信息
    private List<CartPromotionItemResult> cartPromotionItemList;
    //用户收货地址列表
    private List<UmsMemberReceiveAddressVO> memberReceiveAddressList;
    //用户可用优惠券列表
    private List<SmsCouponHistoryDetailResult> couponHistoryDetailList;
    //积分使用规则
    private UmsIntegrationConsumeSettingVO integrationConsumeSetting;
    //会员持有的积分
    private Integer memberIntegration;
    //计算的金额
    private ConfirmOrderRich.CalcAmount calcAmount;

    @Data
    public static class CalcAmount{
        //订单商品总金额
        private BigDecimal totalAmount;
        //运费
        private BigDecimal freightAmount;
        //活动优惠
        private BigDecimal promotionAmount;
        //应付金额
        private BigDecimal payAmount;
    }
}

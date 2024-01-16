package com.xqxls.domain.order.repository;

import com.xqxls.domain.order.model.req.OrderReq;
import com.xqxls.domain.order.model.res.OmsOrderDetailResult;
import com.xqxls.model.*;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/3 21:28
 */
public interface IOmsPortalOrderRepository {


    /**
     * 根据提交信息生成订单
     */
    Map<String, Object> generateOrder(OrderReq orderReq);

    /**
     * 支付成功后的回调
     */
    Integer paySuccess(Long orderId, Integer payType);

    /**
     * 自动取消超时订单
     */
    Integer cancelTimeOutOrder();

    /**
     * 取消单个超时订单
     */
    void cancelOrder(Long orderId);

    /**
     * 发送延迟消息取消订单
     */
    void sendDelayMessageCancelOrder(Long orderId);

    /**
     * 确认收货
     */
    void confirmReceiveOrder(Long orderId);

    /**
     * 分页获取用户订单
     */
    List<OmsOrderDetailResult> list(Integer status, Integer pageNum, Integer pageSize);

    /**
     * 根据订单ID获取订单详情
     */
    OmsOrderDetailResult detail(Long orderId);

    /**
     * 用户根据订单ID删除订单
     */
    void deleteOrder(Long orderId);

    /**
     * 根据orderSn来实现的支付成功逻辑
     */
    void paySuccessByOrderSn(String orderSn, Integer payType);

    /**
     * 根据主键ID查询
     * @return 积分规则
     */
    UmsIntegrationConsumeSetting selectUmsIntegrationConsumeById(Long id);

    /**
     * 根据主键ID查询
     * @return Sku
     */
    PmsSkuStock selectSkuById(Long id);

    /**
     * 更新Sku
     */
    void updateSkuById(PmsSkuStock skuStock);

    List<OmsOrderSetting> selectAllOrderSetting();

    void save(OmsOrder omsOrder);

    void saveOrderItemBatch(List<OmsOrderItem> orderItemList);

    void updateCouponStatus(Long couponId, Long memberId, Integer useStatus);
}

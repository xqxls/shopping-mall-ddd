package com.xqxls.domain.order.service;

import com.xqxls.model.*;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/16 22:40
 */
public interface OrderService {

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

    /**
     * 获取所有订单设置
     * @return 订单设置列表
     */
    List<OmsOrderSetting> selectAllOrderSetting();

    void save(OmsOrder omsOrder);

    void saveOrderItemBatch(List<OmsOrderItem> orderItemList);

    void updateCouponStatus(Long couponId, Long memberId, Integer useStatus);
}

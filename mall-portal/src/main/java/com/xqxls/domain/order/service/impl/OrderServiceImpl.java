package com.xqxls.domain.order.service.impl;

import com.xqxls.domain.order.service.OrderService;
import com.xqxls.model.*;
import com.xqxls.repository.order.OmsPortalOrderRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/16 22:41
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OmsPortalOrderRepository omsPortalOrderRepository;

    @Override
    public UmsIntegrationConsumeSetting selectUmsIntegrationConsumeById(Long id) {
        return omsPortalOrderRepository.selectUmsIntegrationConsumeById(id);
    }

    @Override
    public PmsSkuStock selectSkuById(Long id) {
        return omsPortalOrderRepository.selectSkuById(id);
    }

    @Override
    public void updateSkuById(PmsSkuStock skuStock) {
        omsPortalOrderRepository.updateSkuById(skuStock);
    }

    @Override
    public List<OmsOrderSetting> selectAllOrderSetting() {
        return omsPortalOrderRepository.selectAllOrderSetting();
    }

    @Override
    public void save(OmsOrder omsOrder) {
        omsPortalOrderRepository.save(omsOrder);
    }

    @Override
    public void saveOrderItemBatch(List<OmsOrderItem> orderItemList) {
        omsPortalOrderRepository.saveOrderItemBatch(orderItemList);
    }

    @Override
    public void updateCouponStatus(Long couponId, Long memberId, Integer useStatus) {
        omsPortalOrderRepository.updateCouponStatus(couponId,memberId,useStatus);
    }
}

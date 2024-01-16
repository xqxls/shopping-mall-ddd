package com.xqxls.domain.order.service.createOrder;

import com.xqxls.domain.member.service.UmsMemberService;
import com.xqxls.domain.order.model.req.OrderReq;
import com.xqxls.domain.order.service.OmsCartItemService;
import com.xqxls.domain.order.service.OrderService;
import com.xqxls.dto.CartPromotionItem;
import com.xqxls.exception.Asserts;
import com.xqxls.model.OmsOrder;
import com.xqxls.model.OmsOrderItem;
import com.xqxls.model.PmsSkuStock;
import com.xqxls.model.UmsMember;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/16 21:06
 */
public abstract class AbstractCreateOrderService implements ICreateOrderService{

    @Resource
    private UmsMemberService umsMemberService;
    @Resource
    private OmsCartItemService omsCartItemService;
    @Resource
    private OrderService orderService;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> generateOrder(OrderReq orderReq) {
        // 1.校验收货地址
        if(orderReq.getMemberReceiveAddressId()==null){
            Asserts.fail("请选择收货地址！");
        }
        // 2.获取购物车及优惠信息
        UmsMember currentMember = umsMemberService.getCurrentMember();
        List<CartPromotionItem> cartPromotionItemList = omsCartItemService.listPromotionItem(currentMember.getId(), orderReq.getCartIds());
        // 3.判断购物车中商品是否都有库存
        if (!hasStock(cartPromotionItemList)) {
            Asserts.fail("库存不足，无法下单");
        }
        // 4.生成订单商品列表
        List<OmsOrderItem> orderItemList = createOrderItemList(cartPromotionItemList,orderReq,currentMember);
        // 5.进行库存锁定
        lockStock(cartPromotionItemList);
        // 6.生成订单
        OmsOrder order = createOrder(orderItemList,orderReq,currentMember);
        // 7.插入order表和
        doSaveOrder(order);
        // 8.插入order_item表
        doSaveOrderItemList(orderItemList,order);
        // 9.更新优惠券使用状态、扣除积分、删除购物车中的下单商品
        handleCouponAndClearCartItem(orderReq,currentMember,cartPromotionItemList,order);
        // 10.包装结果
        return buildOrderResult(order,orderItemList);
    }

    private Map<String, Object> buildOrderResult(OmsOrder order, List<OmsOrderItem> orderItemList) {
        Map<String, Object> result = new HashMap<>();
        result.put("order", order);
        result.put("orderItemList", orderItemList);
        return result;
    }

    private void handleCouponAndClearCartItem(OrderReq orderReq, UmsMember currentMember, List<CartPromotionItem> cartPromotionItemList,OmsOrder order) {
        if (orderReq.getCouponId() != null) {
            orderService.updateCouponStatus(orderReq.getCouponId(), currentMember.getId(), 1);
        }
        //如使用积分需要扣除积分
        if (orderReq.getUseIntegration() != null) {
            order.setUseIntegration(orderReq.getUseIntegration());
            if(currentMember.getIntegration()==null){
                currentMember.setIntegration(0);
            }
            umsMemberService.updateIntegration(currentMember.getId(), currentMember.getIntegration() - orderReq.getUseIntegration());
        }
        //删除购物车中的下单商品
        deleteCartItemList(cartPromotionItemList, currentMember);
    }


    /**
     * 判断下单商品是否都有库存
     */
    private boolean hasStock(List<CartPromotionItem> cartPromotionItemList) {
        for (CartPromotionItem cartPromotionItem : cartPromotionItemList) {
            if (cartPromotionItem.getRealStock()==null //判断真实库存是否为空
                    ||cartPromotionItem.getRealStock() <= 0 //判断真实库存是否小于0
                    || cartPromotionItem.getRealStock() < cartPromotionItem.getQuantity()) //判断真实库存是否小于下单的数量
            {
                return false;
            }
        }
        return true;
    }

    protected abstract List<OmsOrderItem> createOrderItemList(List<CartPromotionItem> cartPromotionItemList,OrderReq orderReq,UmsMember currentMember);

    protected abstract OmsOrder createOrder(List<OmsOrderItem> orderItemList, OrderReq orderReq,UmsMember currentMember);

    /** 保存订单 **/
    protected abstract void doSaveOrder(OmsOrder order);

    /** 保存订单商品列表 **/
    protected abstract void doSaveOrderItemList(List<OmsOrderItem> orderItemList,OmsOrder order);

    /**
     * 锁定下单商品的所有库存
     */
    private void lockStock(List<CartPromotionItem> cartPromotionItemList) {
        for (CartPromotionItem cartPromotionItem : cartPromotionItemList) {
            PmsSkuStock skuStock = orderService.selectSkuById(cartPromotionItem.getProductSkuId());
            skuStock.setLockStock(skuStock.getLockStock() + cartPromotionItem.getQuantity());
            orderService.updateSkuById(skuStock);
        }
    }

    /**
     * 删除下单商品的购物车信息
     */
    private void deleteCartItemList(List<CartPromotionItem> cartPromotionItemList, UmsMember currentMember) {
        List<Long> ids = new ArrayList<>();
        for (CartPromotionItem cartPromotionItem : cartPromotionItemList) {
            ids.add(cartPromotionItem.getId());
        }
        omsCartItemService.delete(currentMember.getId(), ids);
    }


}

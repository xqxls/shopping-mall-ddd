package com.xqxls.oms.service.impl;

import com.xqxls.oms.model.req.OmsMoneyInfoReq;
import com.xqxls.oms.model.req.OmsOrderDeliveryReq;
import com.xqxls.oms.model.req.OmsOrderReq;
import com.xqxls.oms.model.req.OmsReceiverInfoReq;
import com.xqxls.oms.model.res.OmsOrderDetailResult;
import com.xqxls.oms.model.vo.OmsOrderVO;
import com.xqxls.oms.repository.IOmsOrderRepository;
import com.xqxls.oms.service.OmsOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单管理Service实现类
 * Created by xqxls on 2018/10/11.
 */
@Service
public class OmsOrderServiceImpl implements OmsOrderService {
    @Resource
    private IOmsOrderRepository omsOrderRepository;

    @Override
    public List<OmsOrderVO> list(OmsOrderReq omsOrderReq, Integer pageSize, Integer pageNum) {
        return omsOrderRepository.list(omsOrderReq,pageSize,pageNum);
    }

    @Override
    public int delivery(List<OmsOrderDeliveryReq> deliveryReqList) {
        return omsOrderRepository.delivery(deliveryReqList);
    }

    @Override
    public int close(List<Long> ids, String note) {
        return omsOrderRepository.close(ids,note);
    }

    @Override
    public int delete(List<Long> ids) {
        return omsOrderRepository.delete(ids);
    }

    @Override
    public OmsOrderDetailResult detail(Long id) {
        return omsOrderRepository.detail(id);
    }

    @Override
    public int updateReceiverInfo(OmsReceiverInfoReq receiverInfoReq) {
        return omsOrderRepository.updateReceiverInfo(receiverInfoReq);
    }

    @Override
    public int updateMoneyInfo(OmsMoneyInfoReq moneyInfoReq) {
        return omsOrderRepository.updateMoneyInfo(moneyInfoReq);
    }

    @Override
    public int updateNote(Long id, String note, Integer status) {
        return omsOrderRepository.updateNote(id,note,status);
    }
}

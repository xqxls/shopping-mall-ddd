package com.xqxls.repository.oms;

import com.github.pagehelper.PageHelper;
import com.xqxls.convert.oms.OmsOrderConvert;
import com.xqxls.convert.oms.OmsOrderItemConvert;
import com.xqxls.convert.oms.OmsOrderOperateHistoryConvert;
import com.xqxls.dao.OmsOrderDao;
import com.xqxls.dao.OmsOrderOperateHistoryDao;
import com.xqxls.dto.OmsOrderDeliveryParam;
import com.xqxls.dto.OmsOrderDetail;
import com.xqxls.dto.OmsOrderQueryParam;
import com.xqxls.mapper.OmsOrderMapper;
import com.xqxls.mapper.OmsOrderOperateHistoryMapper;
import com.xqxls.model.OmsOrder;
import com.xqxls.model.OmsOrderOperateHistory;
import com.xqxls.oms.model.req.OmsMoneyInfoReq;
import com.xqxls.oms.model.req.OmsOrderDeliveryReq;
import com.xqxls.oms.model.req.OmsOrderReq;
import com.xqxls.oms.model.req.OmsReceiverInfoReq;
import com.xqxls.oms.model.res.OmsOrderDetailResult;
import com.xqxls.oms.model.vo.OmsOrderVO;
import com.xqxls.oms.repository.IOmsOrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/29 11:33
 */
@Repository
public class OmsOrderRepository implements IOmsOrderRepository {

    @Resource
    private OmsOrderMapper orderMapper;
    @Resource
    private OmsOrderDao orderDao;
    @Resource
    private OmsOrderOperateHistoryDao orderOperateHistoryDao;
    @Resource
    private OmsOrderOperateHistoryMapper orderOperateHistoryMapper;
    
    @Override
    public List<OmsOrderVO> list(OmsOrderReq omsOrderReq, Integer pageSize, Integer pageNum) {
        OmsOrderQueryParam queryParam = new OmsOrderQueryParam();
        BeanUtils.copyProperties(omsOrderReq,queryParam);
        PageHelper.startPage(pageNum, pageSize);
        return OmsOrderConvert.INSTANCE.omsOrderEntityToVOList(orderDao.getList(queryParam));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delivery(List<OmsOrderDeliveryReq> deliveryReqList) {
        List<OmsOrderDeliveryParam> deliveryParamList = OmsOrderConvert.INSTANCE.omsOrderDeliveryReqToParamList(deliveryReqList);
        //批量发货
        int count = orderDao.delivery(deliveryParamList);
        //添加操作记录
        List<OmsOrderOperateHistory> operateHistoryList = deliveryParamList.stream()
                .map(omsOrderDeliveryParam -> {
                    OmsOrderOperateHistory history = new OmsOrderOperateHistory();
                    history.setOrderId(omsOrderDeliveryParam.getOrderId());
                    history.setCreateTime(new Date());
                    history.setOperateMan("后台管理员");
                    history.setOrderStatus(2);
                    history.setNote("完成发货");
                    return history;
                }).collect(Collectors.toList());
        orderOperateHistoryDao.insertList(operateHistoryList);
        return count;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int close(List<Long> ids, String note) {
        OmsOrder record = new OmsOrder();
        record.setStatus(4);
        Example example = new Example(OmsOrder.class);
        example.createCriteria().andEqualTo("deleteStatus",0).andIn("id",ids);
        int count = orderMapper.updateByExampleSelective(record, example);
        List<OmsOrderOperateHistory> historyList = ids.stream().map(orderId -> {
            OmsOrderOperateHistory history = new OmsOrderOperateHistory();
            history.setOrderId(orderId);
            history.setCreateTime(new Date());
            history.setOperateMan("后台管理员");
            history.setOrderStatus(4);
            history.setNote("订单关闭:"+note);
            return history;
        }).collect(Collectors.toList());
        orderOperateHistoryDao.insertList(historyList);
        return count;
    }

    @Override
    public int delete(List<Long> ids) {
        OmsOrder record = new OmsOrder();
        record.setDeleteStatus(1);
        Example example = new Example(OmsOrder.class);
        example.createCriteria().andEqualTo("deleteStatus",0).andIn("id",ids);
        return orderMapper.updateByExampleSelective(record, example);
    }

    @Override
    public OmsOrderDetailResult detail(Long id) {
        OmsOrderDetail detail = orderDao.getDetail(id);
        OmsOrderDetailResult result = OmsOrderConvert.INSTANCE.omsOrderDetailToResult(detail);
        result.setOrderItemList(OmsOrderItemConvert.INSTANCE.omsOrderItemEntityToVOList(detail.getOrderItemList()));
        result.setHistoryList(OmsOrderOperateHistoryConvert.INSTANCE.omsOrderOperateHistoryEntityToVOList(detail.getHistoryList()));
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateReceiverInfo(OmsReceiverInfoReq receiverInfoReq) {
        OmsOrder order = new OmsOrder();
        order.setId(receiverInfoReq.getOrderId());
        order.setReceiverName(receiverInfoReq.getReceiverName());
        order.setReceiverPhone(receiverInfoReq.getReceiverPhone());
        order.setReceiverPostCode(receiverInfoReq.getReceiverPostCode());
        order.setReceiverDetailAddress(receiverInfoReq.getReceiverDetailAddress());
        order.setReceiverProvince(receiverInfoReq.getReceiverProvince());
        order.setReceiverCity(receiverInfoReq.getReceiverCity());
        order.setReceiverRegion(receiverInfoReq.getReceiverRegion());
        order.setModifyTime(new Date());
        int count = orderMapper.updateByPrimaryKeySelective(order);
        //插入操作记录
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(receiverInfoReq.getOrderId());
        history.setCreateTime(new Date());
        history.setOperateMan("后台管理员");
        history.setOrderStatus(receiverInfoReq.getStatus());
        history.setNote("修改收货人信息");
        orderOperateHistoryMapper.insert(history);
        return count;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateMoneyInfo(OmsMoneyInfoReq moneyInfoReq) {
        OmsOrder order = new OmsOrder();
        order.setId(moneyInfoReq.getOrderId());
        order.setFreightAmount(moneyInfoReq.getFreightAmount());
        order.setDiscountAmount(moneyInfoReq.getDiscountAmount());
        order.setModifyTime(new Date());
        int count = orderMapper.updateByPrimaryKeySelective(order);
        //插入操作记录
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(moneyInfoReq.getOrderId());
        history.setCreateTime(new Date());
        history.setOperateMan("后台管理员");
        history.setOrderStatus(moneyInfoReq.getStatus());
        history.setNote("修改费用信息");
        orderOperateHistoryMapper.insert(history);
        return count;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateNote(Long id, String note, Integer status) {
        OmsOrder order = new OmsOrder();
        order.setId(id);
        order.setNote(note);
        order.setModifyTime(new Date());
        int count = orderMapper.updateByPrimaryKeySelective(order);
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(id);
        history.setCreateTime(new Date());
        history.setOperateMan("后台管理员");
        history.setOrderStatus(status);
        history.setNote("修改备注信息："+note);
        orderOperateHistoryMapper.insert(history);
        return count;
    }
}

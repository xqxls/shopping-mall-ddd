package com.xqxls.oms.service.impl;

import com.xqxls.oms.model.aggregates.OmsOrderReturnApplyRich;
import com.xqxls.oms.model.req.OmsReturnApplyReq;
import com.xqxls.oms.model.req.OmsUpdateStatusReq;
import com.xqxls.oms.model.vo.OmsOrderReturnApplyVO;
import com.xqxls.oms.repository.IOmsOrderReturnApplyRepository;
import com.xqxls.oms.service.OmsOrderReturnApplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单退货管理Service
 * Created by xqxls on 2018/10/18.
 */
@Service
public class OmsOrderReturnApplyServiceImpl implements OmsOrderReturnApplyService {
    @Resource
    private IOmsOrderReturnApplyRepository omsOrderReturnApplyRepository;

    @Override
    public List<OmsOrderReturnApplyVO> list(OmsReturnApplyReq omsReturnApplyReq, Integer pageSize, Integer pageNum) {
        return omsOrderReturnApplyRepository.list(omsReturnApplyReq,pageSize,pageNum);
    }

    @Override
    public int delete(List<Long> ids) {
        return omsOrderReturnApplyRepository.delete(ids);
    }

    @Override
    public int updateStatus(Long id, OmsUpdateStatusReq omsUpdateStatusReq) {
        return omsOrderReturnApplyRepository.updateStatus(id,omsUpdateStatusReq);
    }

    @Override
    public OmsOrderReturnApplyRich getItem(Long id) {
        return omsOrderReturnApplyRepository.getItem(id);
    }
}

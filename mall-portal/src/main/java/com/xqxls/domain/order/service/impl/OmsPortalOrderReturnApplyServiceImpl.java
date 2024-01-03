package com.xqxls.domain.order.service.impl;

import com.xqxls.domain.order.model.req.OmsOrderReturnApplyReq;
import com.xqxls.domain.order.repository.IOmsPortalOrderReturnApplyRepository;
import com.xqxls.domain.order.service.OmsPortalOrderReturnApplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 订单退货管理Service实现类
 * Created by macro on 2018/10/17.
 */
@Service
public class OmsPortalOrderReturnApplyServiceImpl implements OmsPortalOrderReturnApplyService {
    @Resource
    private IOmsPortalOrderReturnApplyRepository omsPortalOrderReturnApplyRepository;
    @Override
    public int create(OmsOrderReturnApplyReq returnApplyReq) {
        return omsPortalOrderReturnApplyRepository.create(returnApplyReq);
    }
}

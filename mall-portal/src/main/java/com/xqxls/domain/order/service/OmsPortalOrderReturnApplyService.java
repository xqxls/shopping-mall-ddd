package com.xqxls.domain.order.service;

import com.xqxls.domain.order.model.req.OmsOrderReturnApplyReq;

/**
 * 订单退货管理Service
 * Created by macro on 2018/10/17.
 */
public interface OmsPortalOrderReturnApplyService {
    /**
     * 提交申请
     */
    int create(OmsOrderReturnApplyReq returnApplyReq);
}

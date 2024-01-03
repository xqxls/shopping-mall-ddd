package com.xqxls.domain.order.repository;

import com.xqxls.domain.order.model.req.OmsOrderReturnApplyReq;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/3 9:09
 */
public interface IOmsPortalOrderReturnApplyRepository {

    /**
     * 提交申请
     */
    int create(OmsOrderReturnApplyReq returnApplyReq);
}


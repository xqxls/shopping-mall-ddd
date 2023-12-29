package com.xqxls.oms.repository;

import com.xqxls.oms.model.aggregates.OmsOrderReturnApplyRich;
import com.xqxls.oms.model.req.OmsReturnApplyReq;
import com.xqxls.oms.model.req.OmsUpdateStatusReq;
import com.xqxls.oms.model.vo.OmsOrderReturnApplyVO;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/29 9:40
 */
public interface IOmsOrderReturnApplyRepository {

    /**
     * 分页查询申请
     */
    List<OmsOrderReturnApplyVO> list(OmsReturnApplyReq omsReturnApplyReq, Integer pageSize, Integer pageNum);

    /**
     * 批量删除申请
     */
    int delete(List<Long> ids);

    /**
     * 修改申请状态
     */
    int updateStatus(Long id, OmsUpdateStatusReq omsUpdateStatusReq);

    /**
     * 获取指定申请详情
     */
    OmsOrderReturnApplyRich getItem(Long id);
}

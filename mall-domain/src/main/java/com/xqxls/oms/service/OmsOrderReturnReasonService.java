package com.xqxls.oms.service;

import com.xqxls.oms.model.vo.OmsOrderReturnReasonVO;

import java.util.List;

/**
 * 订单原因管理Service
 * Created by xqxls on 2018/10/17.
 */
public interface OmsOrderReturnReasonService {
    /**
     * 添加订单原因
     */
    int create(OmsOrderReturnReasonVO returnReasonVO);

    /**
     * 修改退货原因
     */
    int update(Long id, OmsOrderReturnReasonVO returnReasonVO);

    /**
     * 批量删除退货原因
     */
    int delete(List<Long> ids);

    /**
     * 分页获取退货原因
     */
    List<OmsOrderReturnReasonVO> list(Integer pageSize, Integer pageNum);

    /**
     * 批量修改退货原因状态
     */
    int updateStatus(List<Long> ids, Integer status);

    /**
     * 获取单个退货原因详情信息
     */
    OmsOrderReturnReasonVO getItem(Long id);
}

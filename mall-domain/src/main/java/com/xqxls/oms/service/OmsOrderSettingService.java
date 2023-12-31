package com.xqxls.oms.service;

import com.xqxls.oms.model.vo.OmsOrderSettingVO;

/**
 * 订单设置Service
 * Created by xqxls on 2018/10/16.
 */
public interface OmsOrderSettingService {
    /**
     * 获取指定订单设置
     */
    OmsOrderSettingVO getItem(Long id);

    /**
     * 修改指定订单设置
     */
    int update(Long id, OmsOrderSettingVO orderSettingVO);
}

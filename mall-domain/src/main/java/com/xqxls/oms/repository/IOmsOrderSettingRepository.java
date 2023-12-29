package com.xqxls.oms.repository;

import com.xqxls.oms.model.vo.OmsOrderSettingVO;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/29 9:27
 */
public interface IOmsOrderSettingRepository {

    /**
     * 获取指定订单设置
     */
    OmsOrderSettingVO getItem(Long id);

    /**
     * 修改指定订单设置
     */
    int update(Long id, OmsOrderSettingVO orderSettingVO);
}

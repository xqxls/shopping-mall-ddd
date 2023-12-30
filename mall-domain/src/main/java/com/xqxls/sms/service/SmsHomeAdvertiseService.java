package com.xqxls.sms.service;


import com.xqxls.sms.model.vo.SmsHomeAdvertiseVO;

import java.util.List;

/**
 * 首页广告管理Service
 * Created by xqxls on 2018/11/7.
 */
public interface SmsHomeAdvertiseService {
    /**
     * 添加广告
     */
    int create(SmsHomeAdvertiseVO advertise);

    /**
     * 批量删除广告
     */
    int delete(List<Long> ids);

    /**
     * 修改上、下线状态
     */
    int updateStatus(Long id, Integer status);

    /**
     * 获取广告详情
     */
    SmsHomeAdvertiseVO getItem(Long id);

    /**
     * 更新广告
     */
    int update(Long id, SmsHomeAdvertiseVO advertise);

    /**
     * 分页查询广告
     */
    List<SmsHomeAdvertiseVO> list(String name, Integer type, String endTime, Integer pageSize, Integer pageNum);
}

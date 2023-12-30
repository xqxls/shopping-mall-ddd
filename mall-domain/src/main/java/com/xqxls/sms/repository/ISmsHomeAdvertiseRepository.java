package com.xqxls.sms.repository;

import com.xqxls.sms.model.vo.SmsHomeAdvertiseVO;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/30 17:28
 */
public interface ISmsHomeAdvertiseRepository {

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

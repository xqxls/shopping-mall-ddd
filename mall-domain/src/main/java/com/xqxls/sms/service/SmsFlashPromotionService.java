package com.xqxls.sms.service;

import com.xqxls.sms.model.vo.SmsFlashPromotionVO;

import java.util.List;

/**
 * 限时购活动管理Service
 * Created by xqxls on 2018/11/16.
 */
public interface SmsFlashPromotionService {
    /**
     * 添加活动
     */
    int create(SmsFlashPromotionVO flashPromotionVO);

    /**
     * 修改指定活动
     */
    int update(Long id, SmsFlashPromotionVO flashPromotionVO);

    /**
     * 删除单个活动
     */
    int delete(Long id);

    /**
     * 修改上下线状态
     */
    int updateStatus(Long id, Integer status);

    /**
     * 获取详细信息
     */
    SmsFlashPromotionVO getItem(Long id);

    /**
     * 分页查询活动
     */
    List<SmsFlashPromotionVO> list(String keyword, Integer pageSize, Integer pageNum);
}

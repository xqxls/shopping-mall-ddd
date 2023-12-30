package com.xqxls.sms.repository;

import com.xqxls.sms.model.vo.SmsFlashPromotionVO;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/30 16:55
 */
public interface ISmsFlashPromotionRepository {

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

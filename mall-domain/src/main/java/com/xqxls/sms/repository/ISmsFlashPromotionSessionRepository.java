package com.xqxls.sms.repository;

import com.xqxls.sms.model.res.SmsFlashPromotionSessionDetailResult;
import com.xqxls.sms.model.vo.SmsFlashPromotionSessionVO;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/30 17:12
 */
public interface ISmsFlashPromotionSessionRepository {

    /**
     * 添加场次
     */
    int create(SmsFlashPromotionSessionVO promotionSessionVO);

    /**
     * 修改场次
     */
    int update(Long id, SmsFlashPromotionSessionVO promotionSessionVO);

    /**
     * 修改场次启用状态
     */
    int updateStatus(Long id, Integer status);

    /**
     * 删除场次
     */
    int delete(Long id);

    /**
     * 获取详情
     */
    SmsFlashPromotionSessionVO getItem(Long id);

    /**
     * 根据启用状态获取场次列表
     */
    List<SmsFlashPromotionSessionVO> list();

    /**
     * 获取全部可选场次及其数量
     */
    List<SmsFlashPromotionSessionDetailResult> selectList(Long flashPromotionId);
}

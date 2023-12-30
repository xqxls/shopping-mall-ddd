package com.xqxls.sms.service;

import com.xqxls.sms.model.res.SmsFlashPromotionProductResult;
import com.xqxls.sms.model.vo.SmsFlashPromotionProductRelationVO;

import java.util.List;

/**
 * 限时购商品关联管理Service
 * Created by xqxls on 2018/11/16.
 */
public interface SmsFlashPromotionProductRelationService {
    /**
     * 批量添加关联
     */
    int create(List<SmsFlashPromotionProductRelationVO> relationList);

    /**
     * 修改关联相关信息
     */
    int update(Long id, SmsFlashPromotionProductRelationVO relation);

    /**
     * 删除关联
     */
    int delete(Long id);

    /**
     * 获取关联详情
     */
    SmsFlashPromotionProductRelationVO getItem(Long id);

    /**
     * 分页查询相关商品及促销信息
     *
     * @param flashPromotionId        限时购id
     * @param flashPromotionSessionId 限时购场次id
     */
    List<SmsFlashPromotionProductResult> list(Long flashPromotionId, Long flashPromotionSessionId, Integer pageSize, Integer pageNum);

    /**
     * 根据活动和场次id获取商品关系数量
     * @param flashPromotionId 限时购id
     * @param flashPromotionSessionId 限时购场次id
     */
    long getCount(Long flashPromotionId,Long flashPromotionSessionId);
}

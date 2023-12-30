package com.xqxls.sms.service;

import com.xqxls.sms.model.vo.SmsHomeBrandVO;

import java.util.List;

/**
 * 首页品牌管理Service
 * Created by xqxls on 2018/11/6.
 */
public interface SmsHomeBrandService {
    /**
     * 添加首页品牌推荐
     */
    int create(List<SmsHomeBrandVO> homeBrandVOList);

    /**
     * 修改品牌推荐排序
     */
    int updateSort(Long id, Integer sort);

    /**
     * 批量删除品牌推荐
     */
    int delete(List<Long> ids);

    /**
     * 更新推荐状态
     */
    int updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    /**
     * 分页查询品牌推荐
     */
    List<SmsHomeBrandVO> list(String brandName, Integer recommendStatus, Integer pageSize, Integer pageNum);
}

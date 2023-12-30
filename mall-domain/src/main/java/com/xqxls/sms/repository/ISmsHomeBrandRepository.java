package com.xqxls.sms.repository;

import com.xqxls.sms.model.vo.SmsHomeBrandVO;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/30 17:37
 */
public interface ISmsHomeBrandRepository {
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

package com.xqxls.sms.service;

import com.xqxls.sms.model.vo.SmsHomeRecommendSubjectVO;

import java.util.List;

/**
 * 首页专题推荐管理Service
 * Created by xqxls on 2018/11/7.
 */
public interface SmsHomeRecommendSubjectService {
    /**
     * 添加首页推荐
     */
    int create(List<SmsHomeRecommendSubjectVO> recommendSubjectVOList);

    /**
     * 修改推荐排序
     */
    int updateSort(Long id, Integer sort);

    /**
     * 批量删除推荐
     */
    int delete(List<Long> ids);

    /**
     * 更新推荐状态
     */
    int updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    /**
     * 分页查询推荐
     */
    List<SmsHomeRecommendSubjectVO> list(String subjectName, Integer recommendStatus, Integer pageSize, Integer pageNum);
}


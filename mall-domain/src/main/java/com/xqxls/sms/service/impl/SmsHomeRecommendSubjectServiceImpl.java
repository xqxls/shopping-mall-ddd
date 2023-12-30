package com.xqxls.sms.service.impl;

import com.xqxls.sms.model.vo.SmsHomeRecommendSubjectVO;
import com.xqxls.sms.repository.ISmsHomeRecommendSubjectRepository;
import com.xqxls.sms.service.SmsHomeRecommendSubjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 首页专题推荐管理Service实现类
 * Created by xqxls on 2018/11/7.
 */
@Service
public class SmsHomeRecommendSubjectServiceImpl implements SmsHomeRecommendSubjectService {
    @Resource
    private ISmsHomeRecommendSubjectRepository smsHomeRecommendSubjectRepository;

    @Override
    public int create(List<SmsHomeRecommendSubjectVO> recommendSubjectVOList) {
        return smsHomeRecommendSubjectRepository.create(recommendSubjectVOList);
    }

    @Override
    public int updateSort(Long id, Integer sort) {
        return smsHomeRecommendSubjectRepository.updateSort(id,sort);
    }

    @Override
    public int delete(List<Long> ids) {
        return smsHomeRecommendSubjectRepository.delete(ids);
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        return smsHomeRecommendSubjectRepository.updateRecommendStatus(ids,recommendStatus);
    }

    @Override
    public List<SmsHomeRecommendSubjectVO> list(String subjectName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        return smsHomeRecommendSubjectRepository.list(subjectName,recommendStatus,pageSize,pageNum);
    }
}

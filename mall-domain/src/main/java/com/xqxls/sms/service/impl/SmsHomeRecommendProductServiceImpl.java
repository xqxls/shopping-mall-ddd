package com.xqxls.sms.service.impl;

import com.xqxls.sms.model.vo.SmsHomeRecommendProductVO;
import com.xqxls.sms.repository.ISmsHomeRecommendProductRepository;
import com.xqxls.sms.service.SmsHomeRecommendProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 首页人气推荐管理Service实现类
 * Created by xqxls on 2018/11/7.
 */
@Service
public class SmsHomeRecommendProductServiceImpl implements SmsHomeRecommendProductService {
    @Resource
    private ISmsHomeRecommendProductRepository smsHomeRecommendProductRepository;

    @Override
    public int create(List<SmsHomeRecommendProductVO> homeRecommendProductVOList) {
        return smsHomeRecommendProductRepository.create(homeRecommendProductVOList);
    }

    @Override
    public int updateSort(Long id, Integer sort) {
        return smsHomeRecommendProductRepository.updateSort(id,sort);
    }

    @Override
    public int delete(List<Long> ids) {
        return smsHomeRecommendProductRepository.delete(ids);
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        return smsHomeRecommendProductRepository.updateRecommendStatus(ids,recommendStatus);
    }

    @Override
    public List<SmsHomeRecommendProductVO> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        return smsHomeRecommendProductRepository.list(productName,recommendStatus,pageSize,pageNum);
    }
}

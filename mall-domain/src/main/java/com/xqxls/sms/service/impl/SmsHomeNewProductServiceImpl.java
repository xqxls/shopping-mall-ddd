package com.xqxls.sms.service.impl;

import com.xqxls.sms.model.vo.SmsHomeNewProductVO;
import com.xqxls.sms.repository.ISmsHomeNewProductRepository;
import com.xqxls.sms.service.SmsHomeNewProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 首页新品推荐管理Service实现类
 * Created by xqxls on 2018/11/6.
 */
@Service
public class SmsHomeNewProductServiceImpl implements SmsHomeNewProductService {
    @Resource
    private ISmsHomeNewProductRepository smsHomeNewProductRepository;

    @Override
    public int create(List<SmsHomeNewProductVO> homeNewProductList) {
        return smsHomeNewProductRepository.create(homeNewProductList);
    }

    @Override
    public int updateSort(Long id, Integer sort) {
        return smsHomeNewProductRepository.updateSort(id,sort);
    }

    @Override
    public int delete(List<Long> ids) {
        return smsHomeNewProductRepository.delete(ids);
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        return smsHomeNewProductRepository.updateRecommendStatus(ids,recommendStatus);
    }

    @Override
    public List<SmsHomeNewProductVO> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        return smsHomeNewProductRepository.list(productName,recommendStatus,pageSize,pageNum);
    }
}

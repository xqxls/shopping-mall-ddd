package com.xqxls.sms.service.impl;

import com.xqxls.sms.model.vo.SmsHomeBrandVO;
import com.xqxls.sms.repository.ISmsHomeBrandRepository;
import com.xqxls.sms.service.SmsHomeBrandService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 首页品牌管理Service实现类
 * Created by xqxls on 2018/11/6.
 */
@Service
public class SmsHomeBrandServiceImpl implements SmsHomeBrandService {
    @Resource
    private ISmsHomeBrandRepository smsHomeBrandRepository;

    @Override
    public int create(List<SmsHomeBrandVO> homeBrandVOList) {
        return smsHomeBrandRepository.create(homeBrandVOList);
    }

    @Override
    public int updateSort(Long id, Integer sort) {
        return smsHomeBrandRepository.updateSort(id,sort);
    }

    @Override
    public int delete(List<Long> ids) {
        return smsHomeBrandRepository.delete(ids);
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        return smsHomeBrandRepository.updateRecommendStatus(ids,recommendStatus);
    }

    @Override
    public List<SmsHomeBrandVO> list(String brandName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        return smsHomeBrandRepository.list(brandName,recommendStatus,pageSize,pageNum);
    }
}

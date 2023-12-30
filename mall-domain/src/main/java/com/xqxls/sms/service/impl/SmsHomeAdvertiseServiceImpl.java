package com.xqxls.sms.service.impl;

import com.xqxls.sms.model.vo.SmsHomeAdvertiseVO;
import com.xqxls.sms.repository.ISmsHomeAdvertiseRepository;
import com.xqxls.sms.service.SmsHomeAdvertiseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 首页广告管理Service实现类
 * Created by xqxls on 2018/11/7.
 */
@Service
public class SmsHomeAdvertiseServiceImpl implements SmsHomeAdvertiseService {
    @Resource
    private ISmsHomeAdvertiseRepository smsHomeAdvertiseRepository;

    @Override
    public int create(SmsHomeAdvertiseVO advertise) {
        return smsHomeAdvertiseRepository.create(advertise);
    }

    @Override
    public int delete(List<Long> ids) {
        return smsHomeAdvertiseRepository.delete(ids);
    }

    @Override
    public int updateStatus(Long id, Integer status) {
        return smsHomeAdvertiseRepository.updateStatus(id,status);
    }

    @Override
    public SmsHomeAdvertiseVO getItem(Long id) {
        return smsHomeAdvertiseRepository.getItem(id);
    }

    @Override
    public int update(Long id, SmsHomeAdvertiseVO advertise) {
        return smsHomeAdvertiseRepository.update(id,advertise);
    }

    @Override
    public List<SmsHomeAdvertiseVO> list(String name, Integer type, String endTime, Integer pageSize, Integer pageNum) {
        return smsHomeAdvertiseRepository.list(name,type,endTime,pageSize,pageNum);
    }
}

package com.xqxls.oms.service.impl;

import com.xqxls.oms.model.vo.OmsOrderSettingVO;
import com.xqxls.oms.repository.IOmsOrderSettingRepository;
import com.xqxls.oms.service.OmsOrderSettingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 订单设置管理Service实现类
 * Created by xqxls on 2018/10/16.
 */
@Service
public class OmsOrderSettingServiceImpl implements OmsOrderSettingService {
    @Resource
    private IOmsOrderSettingRepository orderSettingRepository;

    @Override
    public OmsOrderSettingVO getItem(Long id) {
        return orderSettingRepository.getItem(id);
    }

    @Override
    public int update(Long id, OmsOrderSettingVO orderSettingVO) {
        return orderSettingRepository.update(id,orderSettingVO);
    }
}

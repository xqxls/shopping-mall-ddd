package com.xqxls.oms.service.impl;

import com.xqxls.oms.model.vo.OmsCompanyAddressVO;
import com.xqxls.oms.repository.IOmsCompanyAddressRepository;
import com.xqxls.oms.service.OmsCompanyAddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 收货地址管理Service实现类
 * Created by xqxls on 2018/10/18.
 */
@Service
public class OmsCompanyAddressServiceImpl implements OmsCompanyAddressService {
    @Resource
    private IOmsCompanyAddressRepository omsCompanyAddressRepository;
    @Override
    public List<OmsCompanyAddressVO> list() {
        return omsCompanyAddressRepository.list();
    }
}

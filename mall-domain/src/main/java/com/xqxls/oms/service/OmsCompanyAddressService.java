package com.xqxls.oms.service;

import com.xqxls.oms.model.vo.OmsCompanyAddressVO;

import java.util.List;

/**
 * 收货地址管Service
 * Created by xqxls on 2018/10/18.
 */
public interface OmsCompanyAddressService {
    /**
     * 获取全部收货地址
     */
    List<OmsCompanyAddressVO> list();
}

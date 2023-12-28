package com.xqxls.oms.repository;

import com.xqxls.oms.model.vo.OmsCompanyAddressVO;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/28 22:06
 */
public interface IOmsCompanyAddressRepository {

    /**
     * 获取全部收货地址
     */
    List<OmsCompanyAddressVO> list();
}

package com.xqxls.repository.oms;

import com.xqxls.convert.oms.OmsCompanyAddressConvert;
import com.xqxls.mapper.OmsCompanyAddressMapper;
import com.xqxls.model.OmsCompanyAddressExample;
import com.xqxls.oms.model.vo.OmsCompanyAddressVO;
import com.xqxls.oms.repository.IOmsCompanyAddressRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/28 22:08
 */
@Repository
public class OmsCompanyAddressRepository implements IOmsCompanyAddressRepository {

    @Resource
    private OmsCompanyAddressMapper companyAddressMapper;

    @Override
    public List<OmsCompanyAddressVO> list() {
        return OmsCompanyAddressConvert.INSTANCE.omsCompanyAddressEntityToVOList(companyAddressMapper.selectByExample(new OmsCompanyAddressExample()));
    }
}

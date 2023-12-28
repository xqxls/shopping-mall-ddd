package com.xqxls.convert.oms;

import com.xqxls.model.OmsCompanyAddress;
import com.xqxls.oms.model.vo.OmsCompanyAddressVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/28 22:10
 */
@Mapper
public interface OmsCompanyAddressConvert {

    OmsCompanyAddressConvert INSTANCE = Mappers.getMapper(OmsCompanyAddressConvert.class);

    List<OmsCompanyAddressVO> omsCompanyAddressEntityToVOList(List<OmsCompanyAddress> list);
}

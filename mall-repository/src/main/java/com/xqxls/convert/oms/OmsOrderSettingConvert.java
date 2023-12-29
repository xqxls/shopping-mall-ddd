package com.xqxls.convert.oms;

import com.xqxls.model.OmsOrderSetting;
import com.xqxls.oms.model.vo.OmsOrderSettingVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/29 9:32
 */
@Mapper
public interface OmsOrderSettingConvert {

    OmsOrderSettingConvert INSTANCE = Mappers.getMapper(OmsOrderSettingConvert.class);

    OmsOrderSettingVO omsOrderSettingEntityToVO(OmsOrderSetting omsOrderSetting);

    OmsOrderSetting omsOrderSettingVOToEntity(OmsOrderSettingVO omsOrderSettingVO);
}

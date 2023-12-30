package com.xqxls.convert.sms;

import com.xqxls.model.SmsHomeAdvertise;
import com.xqxls.sms.model.vo.SmsHomeAdvertiseVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/30 17:29
 */
@Mapper
public interface SmsHomeAdvertiseConvert {

    SmsHomeAdvertiseConvert INSTANCE = Mappers.getMapper(SmsHomeAdvertiseConvert.class);

    SmsHomeAdvertise smsHomeAdvertiseVOToEntity(SmsHomeAdvertiseVO smsHomeAdvertiseVO);

    SmsHomeAdvertiseVO smsHomeAdvertiseEntityToVO(SmsHomeAdvertise SmsHomeAdvertise);

    List<SmsHomeAdvertiseVO> smsHomeAdvertiseEntityToVOList(List<SmsHomeAdvertise> smsHomeAdvertiseList);
}


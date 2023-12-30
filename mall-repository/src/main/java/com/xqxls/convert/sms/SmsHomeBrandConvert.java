package com.xqxls.convert.sms;

import com.xqxls.model.SmsHomeBrand;
import com.xqxls.sms.model.vo.SmsHomeBrandVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/30 17:38
 */
@Mapper
public interface SmsHomeBrandConvert {

    SmsHomeBrandConvert INSTANCE = Mappers.getMapper(SmsHomeBrandConvert.class);

    List<SmsHomeBrand> smsHomeBrandVOToEntityList(List<SmsHomeBrandVO> smsHomeBrandVOList);

    List<SmsHomeBrandVO> smsHomeBrandEntityToVOList(List<SmsHomeBrand> smsHomeBrandList);

}

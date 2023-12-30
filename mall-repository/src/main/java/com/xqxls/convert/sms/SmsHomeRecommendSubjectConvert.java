package com.xqxls.convert.sms;

import com.xqxls.model.SmsHomeRecommendSubject;
import com.xqxls.sms.model.vo.SmsHomeRecommendSubjectVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/30 19:46
 */
@Mapper
public interface SmsHomeRecommendSubjectConvert {

    SmsHomeRecommendSubjectConvert INSTANCE = Mappers.getMapper(SmsHomeRecommendSubjectConvert.class);

    List<SmsHomeRecommendSubject> smsHomeRecommendSubjectVOToEntityList(List<SmsHomeRecommendSubjectVO> smsHomeRecommendSubjectVOList);

    List<SmsHomeRecommendSubjectVO> smsHomeRecommendSubjectEntityToVOList(List<SmsHomeRecommendSubject> smsHomeRecommendSubjectList);

}


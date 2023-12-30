package com.xqxls.convert.sms;

import com.xqxls.model.SmsHomeRecommendProduct;
import com.xqxls.sms.model.vo.SmsHomeRecommendProductVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/30 19:36
 */
@Mapper
public interface SmsHomeRecommendProductConvert {

    SmsHomeRecommendProductConvert INSTANCE = Mappers.getMapper(SmsHomeRecommendProductConvert.class);

    List<SmsHomeRecommendProduct> smsHomeRecommendProductVOToEntityList(List<SmsHomeRecommendProductVO> smsHomeRecommendProductVOList);

    List<SmsHomeRecommendProductVO> smsHomeRecommendProductEntityToVOList(List<SmsHomeRecommendProduct> smsHomeRecommendProductList);

}

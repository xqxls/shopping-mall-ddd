package com.xqxls.convert.sms;

import com.xqxls.model.SmsFlashPromotion;
import com.xqxls.sms.model.vo.SmsFlashPromotionVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/30 16:57
 */
@Mapper
public interface SmsFlashPromotionConvert {

    SmsFlashPromotionConvert INSTANCE = Mappers.getMapper(SmsFlashPromotionConvert.class);

    SmsFlashPromotion smsFlashPromotionVOToEntity(SmsFlashPromotionVO smsFlashPromotionVO);

    SmsFlashPromotionVO smsFlashPromotionEntityToVO(SmsFlashPromotion smsFlashPromotion);

    List<SmsFlashPromotionVO> smsFlashPromotionEntityToVOList(List<SmsFlashPromotion> smsFlashPromotionList);
}

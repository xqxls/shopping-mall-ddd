package com.xqxls.convert.sms;

import com.xqxls.model.SmsFlashPromotion;
import com.xqxls.model.SmsFlashPromotionSession;
import com.xqxls.sms.model.vo.SmsFlashPromotionSessionVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/30 17:11
 */
@Mapper
public interface SmsFlashPromotionSessionConvert {

    SmsFlashPromotionSessionConvert INSTANCE = Mappers.getMapper(SmsFlashPromotionSessionConvert.class);

    SmsFlashPromotionSession smsFlashPromotionSessionVOToEntity(SmsFlashPromotionSessionVO smsFlashPromotionSessionVO);

    SmsFlashPromotionSessionVO smsFlashPromotionSessionEntityToVO(SmsFlashPromotionSession smsFlashPromotionSession);

    List<SmsFlashPromotionSessionVO> smsFlashPromotionSessionEntityToVOList(List<SmsFlashPromotionSession> smsFlashPromotionSessionList);
}

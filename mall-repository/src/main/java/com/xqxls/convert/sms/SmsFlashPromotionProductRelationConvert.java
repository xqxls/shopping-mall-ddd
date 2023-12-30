package com.xqxls.convert.sms;

import com.xqxls.model.SmsFlashPromotionProductRelation;
import com.xqxls.sms.model.vo.SmsFlashPromotionProductRelationVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/30 16:38
 */
@Mapper
public interface SmsFlashPromotionProductRelationConvert {

    SmsFlashPromotionProductRelationConvert INSTANCE = Mappers.getMapper(SmsFlashPromotionProductRelationConvert.class);
    List<SmsFlashPromotionProductRelation> smsFlashPromotionProductRelationVOToEntityList(List<SmsFlashPromotionProductRelationVO> smsFlashPromotionProductRelationVOList);

    SmsFlashPromotionProductRelation smsFlashPromotionProductRelationVOToEntity(SmsFlashPromotionProductRelationVO smsFlashPromotionProductRelationVO);

    SmsFlashPromotionProductRelationVO smsFlashPromotionProductRelationEntityToVO(SmsFlashPromotionProductRelation smsFlashPromotionProductRelation);
}

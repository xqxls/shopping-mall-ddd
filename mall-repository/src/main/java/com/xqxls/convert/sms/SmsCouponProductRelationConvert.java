package com.xqxls.convert.sms;

import com.xqxls.model.SmsCouponProductRelation;
import com.xqxls.sms.model.vo.SmsCouponProductRelationVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/30 16:07
 */
@Mapper
public interface SmsCouponProductRelationConvert {

    SmsCouponProductRelationConvert INSTANCE = Mappers.getMapper(SmsCouponProductRelationConvert.class);
    List<SmsCouponProductRelation> smsCouponProductRelationVOToEntityList(List<SmsCouponProductRelationVO> smsCouponProductRelationVOList);

    List<SmsCouponProductRelationVO> smsCouponProductRelationEntityToVOList(List<SmsCouponProductRelation> smsCouponProductRelationList);
}

package com.xqxls.convert.sms;

import com.xqxls.model.SmsCouponProductCategoryRelation;
import com.xqxls.sms.model.vo.SmsCouponProductCategoryRelationVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/30 16:08
 */
@Mapper
public interface SmsCouponProductCategoryRelationConvert {

    SmsCouponProductCategoryRelationConvert INSTANCE = Mappers.getMapper(SmsCouponProductCategoryRelationConvert.class);
    List<SmsCouponProductCategoryRelation> smsCouponProductCategoryRelationVOToEntityList(List<SmsCouponProductCategoryRelationVO> smsCouponProductCategoryRelationVOList);

    List<SmsCouponProductCategoryRelationVO> smsCouponProductCategoryRelationEntityToVOList(List<SmsCouponProductCategoryRelation> smsCouponProductCategoryRelationList);
}
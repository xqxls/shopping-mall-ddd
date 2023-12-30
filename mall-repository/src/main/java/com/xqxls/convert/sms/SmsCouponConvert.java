package com.xqxls.convert.sms;

import com.xqxls.model.SmsCoupon;
import com.xqxls.sms.model.vo.SmsCouponVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/30 16:04
 */
@Mapper
public interface SmsCouponConvert {

    SmsCouponConvert INSTANCE = Mappers.getMapper(SmsCouponConvert.class);
    SmsCoupon smsCouponVOToEntity(SmsCouponVO smsCouponVO);

    SmsCouponVO smsCouponEntityToVO(SmsCoupon smsCoupon);

    List<SmsCouponVO> smsCouponEntityToVOList(List<SmsCoupon> smsCouponList);
}

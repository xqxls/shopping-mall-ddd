package com.xqxls.convert.member;

import com.xqxls.domain.member.model.vo.SmsCouponHistoryVO;
import com.xqxls.domain.member.model.vo.SmsCouponVO;
import com.xqxls.model.SmsCoupon;
import com.xqxls.model.SmsCouponHistory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/2 17:16
 */
@Mapper
public interface UmsMemberCouponConvert {

    UmsMemberCouponConvert INSTANCE = Mappers.getMapper(UmsMemberCouponConvert.class);

    SmsCouponHistoryVO smsCouponHistoryEntityToVO(SmsCouponHistory entity);

    List<SmsCouponHistoryVO> smsCouponHistoryEntityToVOList(List<SmsCouponHistory> entity);

    SmsCouponVO smsCouponEntityToVO(SmsCoupon entity);

    List<SmsCouponVO> smsCouponEntityToVOList(List<SmsCoupon> entity);
}


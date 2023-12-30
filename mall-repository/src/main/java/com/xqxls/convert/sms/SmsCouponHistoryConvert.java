package com.xqxls.convert.sms;

import com.xqxls.model.SmsCouponHistory;
import com.xqxls.sms.model.vo.SmsCouponHistoryVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/29 22:51
 */
@Mapper
public interface SmsCouponHistoryConvert {

    SmsCouponHistoryConvert INSTANCE = Mappers.getMapper(SmsCouponHistoryConvert.class);

    List<SmsCouponHistoryVO> smsCouponHistoryEntityToVOList(List<SmsCouponHistory> list);

}

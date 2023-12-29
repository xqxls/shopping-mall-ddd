package com.xqxls.convert.oms;

import com.xqxls.model.OmsOrderReturnReason;
import com.xqxls.oms.model.vo.OmsOrderReturnReasonVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/29 11:10
 */
@Mapper
public interface OmsOrderReturnReasonConvert {

    OmsOrderReturnReasonConvert INSTANCE = Mappers.getMapper(OmsOrderReturnReasonConvert.class);

    OmsOrderReturnReasonVO omsOrderReturnReasonEntityToVO(OmsOrderReturnReason omsOrderReturnReason);

    List<OmsOrderReturnReasonVO> omsOrderReturnReasonEntityToVOList(List<OmsOrderReturnReason> omsOrderReturnReasonList);

    OmsOrderReturnReason omsOrderReturnReasonVOToEntity(OmsOrderReturnReasonVO omsOrderReturnReasonVO);
}

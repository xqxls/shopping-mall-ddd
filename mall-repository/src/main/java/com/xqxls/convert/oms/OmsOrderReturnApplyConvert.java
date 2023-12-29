package com.xqxls.convert.oms;

import com.xqxls.dto.OmsReturnApplyQueryParam;
import com.xqxls.model.OmsOrderReturnApply;
import com.xqxls.oms.model.req.OmsReturnApplyReq;
import com.xqxls.oms.model.vo.OmsOrderReturnApplyVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/29 10:02
 */
@Mapper
public interface OmsOrderReturnApplyConvert {

    OmsOrderReturnApplyConvert INSTANCE = Mappers.getMapper(OmsOrderReturnApplyConvert.class);

    OmsReturnApplyQueryParam omsReturnApplyReqToQuery(OmsReturnApplyReq omsReturnApplyReq);

    OmsOrderReturnApplyVO omsReturnApplyEntityToVO(OmsOrderReturnApply omsOrderReturnApply);

    List<OmsOrderReturnApplyVO> omsReturnApplyEntityToVOList(List<OmsOrderReturnApply> omsOrderReturnApplyList);

}

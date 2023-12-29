package com.xqxls.convert.oms;

import com.xqxls.dto.OmsOrderDeliveryParam;
import com.xqxls.dto.OmsOrderDetail;
import com.xqxls.model.OmsOrder;
import com.xqxls.oms.model.req.OmsOrderDeliveryReq;
import com.xqxls.oms.model.res.OmsOrderDetailResult;
import com.xqxls.oms.model.vo.OmsOrderVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/29 11:36
 */
@Mapper
public interface OmsOrderConvert {

    OmsOrderConvert INSTANCE = Mappers.getMapper(OmsOrderConvert.class);

    OmsOrderDetailResult omsOrderDetailToResult(OmsOrderDetail detail);

    List<OmsOrderVO> omsOrderEntityToVOList(List<OmsOrder> list);

    List<OmsOrderDeliveryParam> omsOrderDeliveryReqToParamList(List<OmsOrderDeliveryReq> list);
}

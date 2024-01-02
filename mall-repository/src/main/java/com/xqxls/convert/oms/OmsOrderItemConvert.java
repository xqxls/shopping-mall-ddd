package com.xqxls.convert.oms;

import com.xqxls.model.OmsOrder;
import com.xqxls.model.OmsOrderItem;
import com.xqxls.oms.model.vo.OmsOrderItemVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/29 11:43
 */
@Mapper
public interface OmsOrderItemConvert {

    OmsOrderItemConvert INSTANCE = Mappers.getMapper(OmsOrderItemConvert.class);

    List<OmsOrderItemVO> omsOrderItemEntityToVOList(List<OmsOrderItem> list);
}

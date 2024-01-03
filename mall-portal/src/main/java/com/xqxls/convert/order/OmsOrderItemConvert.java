package com.xqxls.convert.order;

import com.xqxls.domain.order.model.vo.OmsOrderItemVO;
import com.xqxls.model.OmsOrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/3 22:25
 */
@Mapper
public interface OmsOrderItemConvert {

    OmsOrderItemConvert INSTANCE = Mappers.getMapper(OmsOrderItemConvert.class);

    OmsOrderItemVO convertEntityToVO(OmsOrderItem entity);

    List<OmsOrderItemVO> convertEntityToVOList(List<OmsOrderItem> list);


}

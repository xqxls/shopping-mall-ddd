package com.xqxls.convert.order;

import com.xqxls.domain.order.model.vo.OmsCartItemVO;
import com.xqxls.model.OmsCartItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/3 17:18
 */
@Mapper
public interface OmsCartItemConvert {

    OmsCartItemConvert INSTANCE = Mappers.getMapper(OmsCartItemConvert.class);

    OmsCartItemVO convertEntityToVO(OmsCartItem entity);

    List<OmsCartItemVO> convertEntityToVOList(List<OmsCartItem> list);

}

package com.xqxls.convert.order;

import com.xqxls.domain.order.model.res.CartPromotionItemResult;
import com.xqxls.domain.order.model.vo.OmsCartItemVO;
import com.xqxls.dto.CartPromotionItem;
import com.xqxls.model.OmsCartItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/3 17:04
 */
@Mapper
public interface OmsPromotionConvert {

    OmsPromotionConvert INSTANCE = Mappers.getMapper(OmsPromotionConvert.class);

    OmsCartItem omsCartItemVOToEntity(OmsCartItemVO entity);

    List<OmsCartItem> omsCartItemVOToEntityList(List<OmsCartItemVO> list);

    CartPromotionItemResult cartPromotionItemEntityToResult(CartPromotionItem entity);

    List<CartPromotionItemResult> cartPromotionItemEntityToResultList(List<CartPromotionItem> list);

}

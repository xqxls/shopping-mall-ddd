package com.xqxls.convert.product;

import com.xqxls.domain.product.model.vo.PmsProductCategoryVO;
import com.xqxls.model.PmsProductCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/4 19:16
 */
@Mapper
public interface PmsProductCategoryConvert {

    PmsProductCategoryConvert INSTANCE = Mappers.getMapper(PmsProductCategoryConvert.class);

    PmsProductCategoryVO convertEntityToVO(PmsProductCategory entity);

    List<PmsProductCategoryVO> convertEntityToVOList(List<PmsProductCategory> list);

}

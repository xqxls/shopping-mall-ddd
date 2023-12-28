package com.xqxls.convert.pms;

import com.xqxls.model.PmsProductAttributeCategory;
import com.xqxls.pms.model.vo.PmsProductAttributeCategoryVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/28 11:05
 */
@Mapper
public interface PmsProductAttributeCategoryConvert {

    PmsProductAttributeCategoryConvert INSTANCE = Mappers.getMapper(PmsProductAttributeCategoryConvert.class);

    PmsProductAttributeCategoryVO pmsProductAttributeCategoryEntityToVO(PmsProductAttributeCategory pmsProductAttributeCategory);

    List<PmsProductAttributeCategoryVO> pmsProductAttributeCategoryEntityToVOList(List<PmsProductAttributeCategory> list);

}

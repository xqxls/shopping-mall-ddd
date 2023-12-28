package com.xqxls.convert.pms;

import com.xqxls.model.PmsProductCategory;
import com.xqxls.pms.model.vo.PmsProductCategoryVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/28 14:50
 */
@Mapper
public interface PmsProductCategoryConvert {

    PmsProductCategoryConvert INSTANCE = Mappers.getMapper(PmsProductCategoryConvert.class);

    PmsProductCategoryVO pmsProductCategoryEntityToVO(PmsProductCategory pmsProductCategory);

    List<PmsProductCategoryVO> pmsProductCategoryEntityToVOList(List<PmsProductCategory> list);
}

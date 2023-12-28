package com.xqxls.convert.ums;

import com.xqxls.model.UmsResourceCategory;
import com.xqxls.ums.model.vo.UmsResourceCategoryVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UmsResourceCategoryConvert {

    UmsResourceCategoryConvert INSTANCE = Mappers.getMapper(UmsResourceCategoryConvert.class);

    UmsResourceCategory umsResourceCategoryVOToEntity(UmsResourceCategoryVO umsResourceCategoryVO);

    List<UmsResourceCategoryVO> convertResourceCategoryList(List<UmsResourceCategory> list);
}

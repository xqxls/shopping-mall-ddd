package com.xqxls.convert;

import com.xqxls.model.UmsResource;
import com.xqxls.ums.model.vo.UmsResourceVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UmsResourceConvert {

    UmsResourceConvert INSTANCE = Mappers.getMapper(UmsResourceConvert.class);

    UmsResource umsResourceVOToEntity(UmsResourceVO umsResourceVO);

    UmsResourceVO umsResourceEntityToVO(UmsResource umsResource);

    List<UmsResourceVO> convertResourceList(List<UmsResource> list);
}

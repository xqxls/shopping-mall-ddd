package com.xqxls.convert;

import com.xqxls.model.UmsRole;
import com.xqxls.ums.model.vo.UmsRoleVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UmsRoleConvert {

    UmsRoleConvert INSTANCE = Mappers.getMapper(UmsRoleConvert.class);

    UmsRole umsRoleVOToEntity(UmsRoleVO umsRoleVO);

    UmsRoleVO umsRoleEntityToVO(UmsRole umsRole);

    List<UmsRoleVO>  umsRoleEntityToVOList(List<UmsRole> umsRoleList);
}

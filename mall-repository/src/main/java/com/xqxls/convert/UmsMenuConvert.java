package com.xqxls.convert;

import com.xqxls.model.UmsMenu;
import com.xqxls.ums.model.res.UmsMenuNodeResult;
import com.xqxls.ums.model.vo.UmsMenuVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UmsMenuConvert {

    UmsMenuConvert INSTANCE = Mappers.getMapper(UmsMenuConvert.class);

    UmsMenuNodeResult umsMenuEntityToNode(UmsMenuVO umsMenuVO);


    UmsMenu umsMenuVOToEntity(UmsMenuVO umsMenuVO);

    UmsMenuVO umsMenuEntityToVO(UmsMenu umsMenu);

    List<UmsMenuVO> umsMenuEntityToVOList(List<UmsMenu> umsMenuList);
}

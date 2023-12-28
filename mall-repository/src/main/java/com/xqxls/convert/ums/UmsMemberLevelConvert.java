package com.xqxls.convert.ums;

import com.xqxls.model.UmsMemberLevel;
import com.xqxls.ums.model.vo.UmsMemberLevelVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/27 17:04
 */
@Mapper
public interface UmsMemberLevelConvert {

    UmsMemberLevelConvert INSTANCE = Mappers.getMapper(UmsMemberLevelConvert.class);

    List<UmsMemberLevelVO> convertMemberLevel(List<UmsMemberLevel> list);

}

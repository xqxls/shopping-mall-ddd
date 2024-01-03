package com.xqxls.convert.member;

import com.xqxls.domain.member.model.vo.UmsMemberVO;
import com.xqxls.model.UmsMember;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/2 15:46
 */
@Mapper
public interface UmsMemberConvert {

    UmsMemberConvert INSTANCE = Mappers.getMapper(UmsMemberConvert.class);

    UmsMember convertVOToEntity(UmsMemberVO vo);

    UmsMemberVO convertEntityToVO(UmsMember entity);

    List<UmsMemberVO> convertEntityToVOList(List<UmsMember> list);
}

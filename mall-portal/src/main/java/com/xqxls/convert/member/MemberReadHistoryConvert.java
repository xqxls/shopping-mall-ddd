package com.xqxls.convert.member;

import com.xqxls.dto.MemberReadHistory;
import com.xqxls.domain.member.model.vo.MemberReadHistoryVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/2 16:19
 */
@Mapper
public interface MemberReadHistoryConvert {

    MemberReadHistoryConvert INSTANCE = Mappers.getMapper(MemberReadHistoryConvert.class);

    MemberReadHistory convertVOToEntity(MemberReadHistoryVO vo);

    MemberReadHistoryVO convertEntityToVO(MemberReadHistory entity);

    List<MemberReadHistoryVO> convertEntityToVOList(List<MemberReadHistory> list);
}

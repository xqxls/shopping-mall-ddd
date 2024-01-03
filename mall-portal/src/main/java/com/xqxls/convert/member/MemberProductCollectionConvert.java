package com.xqxls.convert.member;

import com.xqxls.dto.MemberProductCollection;
import com.xqxls.domain.member.model.vo.MemberProductCollectionVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/2 16:11
 */
@Mapper
public interface MemberProductCollectionConvert {

    MemberProductCollectionConvert INSTANCE = Mappers.getMapper(MemberProductCollectionConvert.class);

    MemberProductCollection convertVOToEntity(MemberProductCollectionVO vo);

    MemberProductCollectionVO convertEntityToVO(MemberProductCollection entity);

    List<MemberProductCollectionVO> convertEntityToVOList(List<MemberProductCollection> list);
}

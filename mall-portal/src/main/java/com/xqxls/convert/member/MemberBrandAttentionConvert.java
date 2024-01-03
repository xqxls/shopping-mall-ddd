package com.xqxls.convert.member;

import com.xqxls.dto.MemberBrandAttention;
import com.xqxls.domain.member.model.vo.MemberBrandAttentionVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/2 15:00
 */
@Mapper
public interface MemberBrandAttentionConvert {

    MemberBrandAttentionConvert INSTANCE = Mappers.getMapper(MemberBrandAttentionConvert.class);

    MemberBrandAttention convertVOToEntity(MemberBrandAttentionVO vo);

    MemberBrandAttentionVO convertEntityToVO(MemberBrandAttention entity);

    List<MemberBrandAttentionVO> convertEntityToVOList(List<MemberBrandAttention> list);
}

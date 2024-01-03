package com.xqxls.convert.member;

import com.xqxls.domain.member.model.vo.UmsMemberReceiveAddressVO;
import com.xqxls.model.UmsMemberReceiveAddress;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/2 15:25
 */
@Mapper
public interface UmsMemberReceiveAddressConvert {

    UmsMemberReceiveAddressConvert INSTANCE = Mappers.getMapper(UmsMemberReceiveAddressConvert.class);

    UmsMemberReceiveAddress convertVOToEntity(UmsMemberReceiveAddressVO vo);

    UmsMemberReceiveAddressVO convertEntityToVO(UmsMemberReceiveAddress entity);

    List<UmsMemberReceiveAddressVO> convertEntityToVOList(List<UmsMemberReceiveAddress> list);
}

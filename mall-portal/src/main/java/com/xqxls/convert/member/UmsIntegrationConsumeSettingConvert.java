package com.xqxls.convert.member;

import com.xqxls.domain.member.model.vo.UmsIntegrationConsumeSettingVO;
import com.xqxls.model.UmsIntegrationConsumeSetting;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/3 22:06
 */
@Mapper
public interface UmsIntegrationConsumeSettingConvert {

    UmsIntegrationConsumeSettingConvert INSTANCE = Mappers.getMapper(UmsIntegrationConsumeSettingConvert.class);

    UmsIntegrationConsumeSettingVO convertEntityToVO(UmsIntegrationConsumeSetting entity);

    List<UmsIntegrationConsumeSettingVO> convertEntityToVOList(List<UmsIntegrationConsumeSetting> list);
}

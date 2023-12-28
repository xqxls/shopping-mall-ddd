package com.xqxls.convert.pms;

import com.xqxls.model.CmsPrefrenceAreaProductRelation;
import com.xqxls.pms.model.vo.CmsPrefrenceAreaProductRelationVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/28 16:49
 */
@Mapper
public interface CmsPrefrenceAreaProductRelationConvert {

    CmsPrefrenceAreaProductRelationConvert INSTANCE = Mappers.getMapper(CmsPrefrenceAreaProductRelationConvert.class);

    List<CmsPrefrenceAreaProductRelation> cmsPrefrenceAreaProductRelationVOToEntityList(List<CmsPrefrenceAreaProductRelationVO> list);
}

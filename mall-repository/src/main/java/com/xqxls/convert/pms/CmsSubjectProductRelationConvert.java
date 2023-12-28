package com.xqxls.convert.pms;

import com.xqxls.model.CmsSubjectProductRelation;
import com.xqxls.pms.model.vo.CmsSubjectProductRelationVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/28 16:48
 */
@Mapper
public interface CmsSubjectProductRelationConvert {

    CmsSubjectProductRelationConvert INSTANCE = Mappers.getMapper(CmsSubjectProductRelationConvert.class);

    List<CmsSubjectProductRelation> cmsSubjectProductRelationVOToEntityList(List<CmsSubjectProductRelationVO> list);
}

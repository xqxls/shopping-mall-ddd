package com.xqxls.convert.pms;

import com.xqxls.model.CmsSubject;
import com.xqxls.pms.model.vo.CmsSubjectVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/30 19:59
 */
@Mapper
public interface CmsSubjectConvert {

    CmsSubjectConvert INSTANCE = Mappers.getMapper(CmsSubjectConvert.class);

    List<CmsSubjectVO> cmsSubjectEntityToVOList(List<CmsSubject> list);
}

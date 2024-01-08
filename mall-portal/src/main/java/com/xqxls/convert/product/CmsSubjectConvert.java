package com.xqxls.convert.product;

import com.xqxls.domain.product.model.vo.CmsSubjectVO;
import com.xqxls.model.CmsSubject;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/4 19:19
 */
@Mapper
public interface CmsSubjectConvert {

    CmsSubjectConvert INSTANCE = Mappers.getMapper(CmsSubjectConvert.class);

    CmsSubjectVO convertEntityToVO(CmsSubject entity);

    List<CmsSubjectVO> convertEntityToVOList(List<CmsSubject> list);

}
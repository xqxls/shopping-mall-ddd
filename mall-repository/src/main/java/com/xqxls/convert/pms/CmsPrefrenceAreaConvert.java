package com.xqxls.convert.pms;

import com.xqxls.model.CmsPrefrenceArea;
import com.xqxls.pms.model.vo.CmsPrefrenceAreaVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/30 20:08
 */
@Mapper
public interface CmsPrefrenceAreaConvert {

    CmsPrefrenceAreaConvert INSTANCE = Mappers.getMapper(CmsPrefrenceAreaConvert.class);

    List<CmsPrefrenceAreaVO> cmsPrefrenceAreaEntityToVOList(List<CmsPrefrenceArea> list);
}

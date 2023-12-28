package com.xqxls.convert.pms;

import com.xqxls.model.PmsBrand;
import com.xqxls.pms.model.vo.PmsBrandVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/28 10:11
 */
@Mapper
public interface PmsBrandConvert {

    PmsBrandConvert INSTANCE = Mappers.getMapper(PmsBrandConvert.class);

    PmsBrandVO pmsBrandEntityToVO(PmsBrand pmsBrand);

    List<PmsBrandVO> pmsBrandEntityToVOList(List<PmsBrand> list);
}

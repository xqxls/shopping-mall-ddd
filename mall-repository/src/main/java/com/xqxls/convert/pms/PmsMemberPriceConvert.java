package com.xqxls.convert.pms;

import com.xqxls.model.PmsMemberPrice;
import com.xqxls.pms.model.vo.PmsMemberPriceVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/28 16:47
 */
@Mapper
public interface PmsMemberPriceConvert {

    PmsMemberPriceConvert INSTANCE = Mappers.getMapper(PmsMemberPriceConvert.class);

    List<PmsMemberPrice> pmsMemberPriceVOToEntityList(List<PmsMemberPriceVO> list);
}

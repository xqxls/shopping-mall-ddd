package com.xqxls.convert.pms;

import com.xqxls.model.PmsProductFullReduction;
import com.xqxls.pms.model.vo.PmsProductFullReductionVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/28 16:46
 */
@Mapper
public interface PmsProductFullReductionConvert {

    PmsProductFullReductionConvert INSTANCE = Mappers.getMapper(PmsProductFullReductionConvert.class);

    List<PmsProductFullReduction> pmsProductFullReductionVOToEntityList(List<PmsProductFullReductionVO> list);
}

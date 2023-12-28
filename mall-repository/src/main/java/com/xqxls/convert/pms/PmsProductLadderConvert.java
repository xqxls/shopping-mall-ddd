package com.xqxls.convert.pms;

import com.xqxls.model.PmsProductLadder;
import com.xqxls.pms.model.vo.PmsProductLadderVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/28 16:44
 */
@Mapper
public interface PmsProductLadderConvert {

    PmsProductLadderConvert INSTANCE = Mappers.getMapper(PmsProductLadderConvert.class);

    List<PmsProductLadder> pmsProductLadderVOToEntityList(List<PmsProductLadderVO> list);
}

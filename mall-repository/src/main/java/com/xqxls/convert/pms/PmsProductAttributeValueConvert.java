package com.xqxls.convert.pms;

import com.xqxls.model.PmsProductAttributeValue;
import com.xqxls.pms.model.vo.PmsProductAttributeValueVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/28 16:48
 */
@Mapper
public interface PmsProductAttributeValueConvert {

    PmsProductAttributeValueConvert INSTANCE = Mappers.getMapper(PmsProductAttributeValueConvert.class);

    List<PmsProductAttributeValue> pmsProductAttributeValueVOToEntityList(List<PmsProductAttributeValueVO> list);
}

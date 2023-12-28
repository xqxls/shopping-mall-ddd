package com.xqxls.convert.pms;

import com.xqxls.dto.ProductAttrInfo;
import com.xqxls.model.PmsProductAttribute;
import com.xqxls.pms.model.res.ProductAttrInfoResult;
import com.xqxls.pms.model.vo.PmsProductAttributeVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/28 11:34
 */
@Mapper
public interface PmsProductAttributeConvert {

    PmsProductAttributeConvert INSTANCE = Mappers.getMapper(PmsProductAttributeConvert.class);

    PmsProductAttributeVO pmsProductAttributeEntityToVO(PmsProductAttribute pmsProductAttribute);

    List<ProductAttrInfoResult> pmsProductAttributeInfoToResultList(List<ProductAttrInfo> productAttrInfoList);

    List<PmsProductAttributeVO> pmsProductAttributeEntityToVOList(List<PmsProductAttribute> list);

}

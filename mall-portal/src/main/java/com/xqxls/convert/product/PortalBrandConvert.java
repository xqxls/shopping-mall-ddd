package com.xqxls.convert.product;

import com.xqxls.domain.product.model.vo.PmsBrandVO;
import com.xqxls.model.PmsBrand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/2 20:01
 */
@Mapper
public interface PortalBrandConvert {

    PortalBrandConvert INSTANCE = Mappers.getMapper(PortalBrandConvert.class);

    PmsBrandVO convertEntityToVO(PmsBrand entity);

    List<PmsBrandVO> convertEntityToVOList(List<PmsBrand> list);

}

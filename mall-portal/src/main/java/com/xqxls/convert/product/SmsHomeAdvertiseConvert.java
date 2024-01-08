package com.xqxls.convert.product;

import com.xqxls.domain.product.model.vo.SmsHomeAdvertiseVO;
import com.xqxls.model.SmsHomeAdvertise;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/4 19:22
 */
@Mapper
public interface SmsHomeAdvertiseConvert {

    SmsHomeAdvertiseConvert INSTANCE = Mappers.getMapper(SmsHomeAdvertiseConvert.class);

    SmsHomeAdvertiseVO convertEntityToVO(SmsHomeAdvertise entity);

    List<SmsHomeAdvertiseVO> convertEntityToVOList(List<SmsHomeAdvertise> list);

}

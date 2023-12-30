package com.xqxls.convert.sms;

import com.xqxls.model.SmsHomeNewProduct;
import com.xqxls.sms.model.vo.SmsHomeNewProductVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/30 17:47
 */
@Mapper
public interface SmsHomeNewProductConvert {

    SmsHomeNewProductConvert INSTANCE = Mappers.getMapper(SmsHomeNewProductConvert.class);

    List<SmsHomeNewProduct> smsHomeNewProductVOToEntityList(List<SmsHomeNewProductVO> smsHomeNewProductVOList);

    List<SmsHomeNewProductVO> smsHomeNewProductEntityToVOList(List<SmsHomeNewProduct> smsHomeNewProductList);

}

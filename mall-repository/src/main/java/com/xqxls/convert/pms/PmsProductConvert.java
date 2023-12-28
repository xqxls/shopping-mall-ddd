package com.xqxls.convert.pms;

import com.xqxls.dto.PmsProductResult;
import com.xqxls.model.PmsProduct;
import com.xqxls.pms.model.res.PmsProductUpdateResult;
import com.xqxls.pms.model.vo.PmsProductVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/28 16:38
 */
@Mapper
public interface PmsProductConvert {

    PmsProductConvert INSTANCE = Mappers.getMapper(PmsProductConvert.class);

    PmsProduct pmsProductVOToEntity(PmsProductVO pmsProductVO);

    PmsProductUpdateResult pmsProductResultToUpdateResult(PmsProductResult pmsProductResult);

    PmsProductVO pmsProductEntityToVO(PmsProduct pmsProduct);

    List<PmsProductVO> pmsProductEntityToVOList(List<PmsProduct> pmsProductList);
}

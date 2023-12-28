package com.xqxls.convert.pms;

import com.xqxls.model.PmsSkuStock;
import com.xqxls.pms.model.vo.PmsSkuStockVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/28 15:28
 */
@Mapper
public interface PmsSkuStockConvert {

    PmsSkuStockConvert INSTANCE = Mappers.getMapper(PmsSkuStockConvert.class);

    List<PmsSkuStock> pmsSkuStockVOToEntityList(List<PmsSkuStockVO> list);

    List<PmsSkuStockVO> pmsSkuStockEntityToVOList(List<PmsSkuStock> list);
}

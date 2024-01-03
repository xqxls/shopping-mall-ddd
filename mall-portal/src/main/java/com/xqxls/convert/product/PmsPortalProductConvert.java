package com.xqxls.convert.product;

import com.xqxls.domain.member.model.vo.SmsCouponVO;
import com.xqxls.domain.product.model.vo.*;
import com.xqxls.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/2 20:23
 */
@Mapper
public interface PmsPortalProductConvert {

    PmsPortalProductConvert INSTANCE = Mappers.getMapper(PmsPortalProductConvert.class);

    PmsProductVO convertEntityToVO(PmsProduct entity);

    List<PmsProductVO> convertEntityToVOList(List<PmsProduct> list);

    PmsProductAttributeVO pmsProductAttributeEntityToVO(PmsProductAttribute entity);

    List<PmsProductAttributeVO> pmsProductAttributeEntityToVOList(List<PmsProductAttribute> list);

    PmsProductAttributeValueVO pmsProductAttributeValueEntityToVO(PmsProductAttributeValue entity);

    List<PmsProductAttributeValueVO> pmsProductAttributeValueEntityToVOList(List<PmsProductAttributeValue> list);

    PmsSkuStockVO pmsSkuStockEntityToVO(PmsSkuStock entity);

    List<PmsSkuStockVO> pmsSkuStockEntityToVOList(List<PmsSkuStock> list);

    PmsProductLadderVO pmsProductLadderEntityToVO(PmsProductLadder entity);

    List<PmsProductLadderVO> pmsProductLadderEntityToVOList(List<PmsProductLadder> list);

    PmsProductFullReductionVO pmsProductFullReductionEntityToVO(PmsProductFullReduction entity);

    List<PmsProductFullReductionVO> pmsProductFullReductionEntityToVOList(List<PmsProductFullReduction> list);

    SmsCouponVO smsCouponEntityToVO(SmsCoupon entity);

    List<SmsCouponVO> smsCouponEntityToVOList(List<SmsCoupon> list);



}

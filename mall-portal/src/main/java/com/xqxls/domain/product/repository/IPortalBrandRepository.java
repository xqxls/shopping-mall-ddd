package com.xqxls.domain.product.repository;

import com.xqxls.api.CommonPage;
import com.xqxls.domain.product.model.vo.PmsBrandVO;
import com.xqxls.domain.product.model.vo.PmsProductVO;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/2 20:00
 */
public interface IPortalBrandRepository {

    /**
     * 分页获取推荐品牌
     */
    List<PmsBrandVO> recommendList(Integer pageNum, Integer pageSize);

    /**
     * 获取品牌详情
     */
    PmsBrandVO detail(Long brandId);

    /**
     * 分页获取品牌关联商品
     */
    List<PmsProductVO> productList(Long brandId, Integer pageNum, Integer pageSize);
}

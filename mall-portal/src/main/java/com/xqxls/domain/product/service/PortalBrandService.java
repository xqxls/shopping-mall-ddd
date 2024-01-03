package com.xqxls.domain.product.service;

import com.xqxls.domain.product.model.vo.PmsBrandVO;
import com.xqxls.domain.product.model.vo.PmsProductVO;

import java.util.List;

/**
 * 前台品牌管理Service
 * Created by macro on 2020/5/15.
 */
public interface PortalBrandService {
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

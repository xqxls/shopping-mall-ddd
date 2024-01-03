package com.xqxls.domain.product.service;

import com.xqxls.domain.product.model.aggregates.PmsPortalProductDetailRich;
import com.xqxls.domain.product.model.res.PmsProductCategoryNodeResult;
import com.xqxls.domain.product.model.vo.PmsProductVO;
import com.xqxls.model.PmsProduct;
import com.xqxls.dto.PmsPortalProductDetail;
import com.xqxls.dto.PmsProductCategoryNode;

import java.util.List;

/**
 * 前台商品管理Service
 * Created by macro on 2020/4/6.
 */
public interface PmsPortalProductService {
    /**
     * 综合搜索商品
     */
    List<PmsProductVO> search(String keyword, Long brandId, Long productCategoryId, Integer pageNum, Integer pageSize, Integer sort);

    /**
     * 以树形结构获取所有商品分类
     */
    List<PmsProductCategoryNodeResult> categoryTreeList();

    /**
     * 获取前台商品详情
     */
    PmsPortalProductDetailRich detail(Long id);
}

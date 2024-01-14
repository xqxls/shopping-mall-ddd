package com.xqxls.domain.product.service;

import com.xqxls.domain.product.model.aggregates.PmsPortalProductDetailRich;
import com.xqxls.domain.product.model.res.PmsProductCategoryNodeResult;
import com.xqxls.domain.product.model.vo.PmsProductVO;
import com.xqxls.dto.PromotionProduct;
import com.xqxls.model.OmsCartItem;

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

    /**
     * 查询所有商品的优惠相关信息
     * @param productIdList 商品ID列表
     * @return 商品优惠信息
     */
    List<PromotionProduct> getPromotionProductList(List<Long> productIdList);
}

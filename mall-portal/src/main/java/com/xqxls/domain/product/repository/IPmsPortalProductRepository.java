package com.xqxls.domain.product.repository;

import com.xqxls.domain.product.model.aggregates.PmsPortalProductDetailRich;
import com.xqxls.domain.product.model.res.PmsProductCategoryNodeResult;
import com.xqxls.domain.product.model.vo.PmsProductVO;
import com.xqxls.dto.PromotionProduct;
import com.xqxls.model.OmsCartItem;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/2 20:20
 */
public interface IPmsPortalProductRepository {

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

    List<PromotionProduct> getPromotionProductList(List<Long> productIdList);
}

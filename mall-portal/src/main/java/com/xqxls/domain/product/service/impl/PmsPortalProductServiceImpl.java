package com.xqxls.domain.product.service.impl;

import com.xqxls.domain.product.model.aggregates.PmsPortalProductDetailRich;
import com.xqxls.domain.product.model.res.PmsProductCategoryNodeResult;
import com.xqxls.domain.product.model.vo.PmsProductVO;
import com.xqxls.domain.product.repository.IPmsPortalProductRepository;
import com.xqxls.domain.product.service.PmsPortalProductService;
import com.xqxls.dto.PromotionProduct;
import com.xqxls.model.OmsCartItem;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 前台订单管理Service实现类
 * Created by macro on 2020/4/6.
 */
@Service
public class PmsPortalProductServiceImpl implements PmsPortalProductService {
    @Resource
    private IPmsPortalProductRepository pmsPortalProductRepository;

    @Override
    public List<PmsProductVO> search(String keyword, Long brandId, Long productCategoryId, Integer pageNum, Integer pageSize, Integer sort) {
        return pmsPortalProductRepository.search(keyword,brandId,productCategoryId,pageNum,pageSize,sort);
    }

    @Override
    public List<PmsProductCategoryNodeResult> categoryTreeList() {
        return pmsPortalProductRepository.categoryTreeList();
    }

    @Override
    public PmsPortalProductDetailRich detail(Long id) {

        return pmsPortalProductRepository.detail(id);
    }

    @Override
    public List<PromotionProduct> getPromotionProductList(List<Long> productIdList) {
        return pmsPortalProductRepository.getPromotionProductList(productIdList);
    }

}

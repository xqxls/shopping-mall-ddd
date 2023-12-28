package com.xqxls.pms.service;

import com.xqxls.model.PmsSkuStock;
import com.xqxls.pms.model.vo.PmsSkuStockVO;

import java.util.List;

/**
 * sku商品库存管理Service
 * Created by xqxls on 2018/4/27.
 */
public interface PmsSkuStockService {
    /**
     * 根据产品id和skuCode模糊搜索
     */
    List<PmsSkuStockVO> getList(Long pid, String keyword);

    /**
     * 批量更新商品库存信息
     */
    int update(Long pid, List<PmsSkuStockVO> skuStockList);
}

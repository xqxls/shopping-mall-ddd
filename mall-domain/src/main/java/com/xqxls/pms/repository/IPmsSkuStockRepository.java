package com.xqxls.pms.repository;

import com.xqxls.pms.model.vo.PmsSkuStockVO;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/28 9:33
 */
public interface IPmsSkuStockRepository {

    /**
     * 根据产品id和skuCode模糊搜索
     */
    List<PmsSkuStockVO> getList(Long pid, String keyword);

    /**
     * 批量更新商品库存信息
     */
    int update(Long pid, List<PmsSkuStockVO> skuStockList);
}

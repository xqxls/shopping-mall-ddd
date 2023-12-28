package com.xqxls.pms.service.impl;

import com.xqxls.pms.model.vo.PmsSkuStockVO;
import com.xqxls.pms.repository.IPmsSkuStockRepository;
import com.xqxls.pms.service.PmsSkuStockService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品sku库存管理Service实现类
 * Created by xqxls on 2018/4/27.
 */
@Service
public class PmsSkuStockServiceImpl implements PmsSkuStockService {
    @Resource
    private IPmsSkuStockRepository pmsSkuStockRepository;

    @Override
    public List<PmsSkuStockVO> getList(Long pid, String keyword) {
        return pmsSkuStockRepository.getList(pid,keyword);
    }

    @Override
    public int update(Long pid, List<PmsSkuStockVO> skuStockList) {
        return pmsSkuStockRepository.update(pid,skuStockList);
    }
}

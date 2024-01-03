package com.xqxls.domain.product.service.impl;

import com.xqxls.domain.product.model.vo.PmsBrandVO;
import com.xqxls.domain.product.model.vo.PmsProductVO;
import com.xqxls.domain.product.repository.IPortalBrandRepository;
import com.xqxls.domain.product.service.PortalBrandService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 前台品牌管理Service实现类
 * Created by macro on 2020/5/15.
 */
@Service
public class PortalBrandServiceImpl implements PortalBrandService {
    @Resource
    private IPortalBrandRepository portalBrandRepository;

    @Override
    public List<PmsBrandVO> recommendList(Integer pageNum, Integer pageSize) {
        return portalBrandRepository.recommendList(pageNum,pageSize);
    }

    @Override
    public PmsBrandVO detail(Long brandId) {
        return portalBrandRepository.detail(brandId);
    }

    @Override
    public List<PmsProductVO> productList(Long brandId, Integer pageNum, Integer pageSize) {
        return portalBrandRepository.productList(brandId,pageNum,pageSize);
    }
}

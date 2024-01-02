package com.xqxls.service.impl;

import com.github.pagehelper.PageHelper;
import com.xqxls.api.CommonPage;
import com.xqxls.mapper.PmsBrandMapper;
import com.xqxls.mapper.PmsProductMapper;
import com.xqxls.model.PmsBrand;
import com.xqxls.model.PmsProduct;
import com.xqxls.dao.HomeDao;
import com.xqxls.service.PortalBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * 前台品牌管理Service实现类
 * Created by macro on 2020/5/15.
 */
@Service
public class PortalBrandServiceImpl implements PortalBrandService {
    @Resource
    private HomeDao homeDao;
    @Resource
    private PmsBrandMapper brandMapper;
    @Resource
    private PmsProductMapper productMapper;

    @Override
    public List<PmsBrand> recommendList(Integer pageNum, Integer pageSize) {
        int offset = (pageNum - 1) * pageSize;
        return homeDao.getRecommendBrandList(offset, pageSize);
    }

    @Override
    public PmsBrand detail(Long brandId) {
        return brandMapper.selectByPrimaryKey(brandId);
    }

    @Override
    public CommonPage<PmsProduct> productList(Long brandId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Example example = new Example(PmsProduct.class);
        example.createCriteria().andEqualTo("deleteStatus",0)
                .andEqualTo("brandId",brandId);
        List<PmsProduct> productList = productMapper.selectByExample(example);
        return CommonPage.restPage(productList);
    }
}

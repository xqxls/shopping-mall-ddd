package com.xqxls.repository.product;

import com.github.pagehelper.PageHelper;
import com.xqxls.convert.product.PmsPortalProductConvert;
import com.xqxls.convert.product.PortalBrandConvert;
import com.xqxls.dao.HomeDao;
import com.xqxls.domain.product.model.vo.PmsBrandVO;
import com.xqxls.domain.product.model.vo.PmsProductVO;
import com.xqxls.domain.product.repository.IPortalBrandRepository;
import com.xqxls.mapper.PmsBrandMapper;
import com.xqxls.mapper.PmsProductMapper;
import com.xqxls.model.PmsProduct;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/2 20:04
 */
@Repository
public class PortalBrandRepository implements IPortalBrandRepository {

    @Resource
    private HomeDao homeDao;
    @Resource
    private PmsBrandMapper brandMapper;
    @Resource
    private PmsProductMapper productMapper;


    @Override
    public List<PmsBrandVO> recommendList(Integer pageNum, Integer pageSize) {
        int offset = (pageNum - 1) * pageSize;
        return PortalBrandConvert.INSTANCE.convertEntityToVOList(homeDao.getRecommendBrandList(offset, pageSize));
    }

    @Override
    public PmsBrandVO detail(Long brandId) {
        return PortalBrandConvert.INSTANCE.convertEntityToVO(brandMapper.selectByPrimaryKey(brandId));
    }

    @Override
    public List<PmsProductVO> productList(Long brandId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(PmsProduct.class);
        example.createCriteria().andEqualTo("deleteStatus", 0)
                .andEqualTo("brandId", brandId);
        List<PmsProduct> productList = productMapper.selectByExample(example);
        return PmsPortalProductConvert.INSTANCE.convertEntityToVOList(productList);
    }
}

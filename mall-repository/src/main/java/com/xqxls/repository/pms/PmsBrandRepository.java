package com.xqxls.repository.pms;

import com.github.pagehelper.PageHelper;
import com.xqxls.convert.pms.PmsBrandConvert;
import com.xqxls.mapper.PmsBrandMapper;
import com.xqxls.mapper.PmsProductMapper;
import com.xqxls.model.PmsBrand;
import com.xqxls.model.PmsBrandExample;
import com.xqxls.model.PmsProduct;
import com.xqxls.model.PmsProductExample;
import com.xqxls.pms.model.req.PmsBrandReq;
import com.xqxls.pms.model.vo.PmsBrandVO;
import com.xqxls.pms.repository.IPmsBrandRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/28 10:10
 */
@Repository
public class PmsBrandRepository implements IPmsBrandRepository {

    @Resource
    private PmsBrandMapper brandMapper;
    @Resource
    private PmsProductMapper productMapper;

    @Override
    public List<PmsBrandVO> listAllBrand() {
        return PmsBrandConvert.INSTANCE.pmsBrandEntityToVOList(brandMapper.selectByExample(new PmsBrandExample()));
    }

    @Override
    public int createBrand(PmsBrandReq pmsBrandReq) {
        PmsBrand pmsBrand = new PmsBrand();
        BeanUtils.copyProperties(pmsBrandReq, pmsBrand);
        //如果创建时首字母为空，取名称的第一个为首字母
        if (!StringUtils.hasText(pmsBrand.getFirstLetter())) {
            pmsBrand.setFirstLetter(pmsBrand.getName().substring(0, 1));
        }
        return brandMapper.insertSelective(pmsBrand);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateBrand(Long id, PmsBrandReq pmsBrandReq) {
        PmsBrand pmsBrand = new PmsBrand();
        BeanUtils.copyProperties(pmsBrandReq, pmsBrand);
        pmsBrand.setId(id);
        //如果创建时首字母为空，取名称的第一个为首字母
        if (!StringUtils.hasText(pmsBrand.getFirstLetter())) {
            pmsBrand.setFirstLetter(pmsBrand.getName().substring(0, 1));
        }
        //更新品牌时要更新商品中的品牌名称
        PmsProduct product = new PmsProduct();
        product.setBrandName(pmsBrand.getName());
        PmsProductExample example = new PmsProductExample();
        example.createCriteria().andBrandIdEqualTo(id);
        productMapper.updateByExampleSelective(product,example);
        return brandMapper.updateByPrimaryKeySelective(pmsBrand);
    }

    @Override
    public int deleteBrand(Long id) {
        return brandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteBrand(List<Long> ids) {
        PmsBrandExample pmsBrandExample = new PmsBrandExample();
        pmsBrandExample.createCriteria().andIdIn(ids);
        return brandMapper.deleteByExample(pmsBrandExample);
    }

    @Override
    public List<PmsBrandVO> listBrand(String keyword, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PmsBrandExample pmsBrandExample = new PmsBrandExample();
        pmsBrandExample.setOrderByClause("sort desc");
        PmsBrandExample.Criteria criteria = pmsBrandExample.createCriteria();
        if (StringUtils.hasText(keyword)) {
            criteria.andNameLike("%" + keyword + "%");
        }
        return PmsBrandConvert.INSTANCE.pmsBrandEntityToVOList(brandMapper.selectByExample(pmsBrandExample));
    }

    @Override
    public PmsBrandVO getBrand(Long id) {
        return PmsBrandConvert.INSTANCE.pmsBrandEntityToVO(brandMapper.selectByPrimaryKey(id));
    }

    @Override
    public int updateShowStatus(List<Long> ids, Integer showStatus) {
        PmsBrand pmsBrand = new PmsBrand();
        pmsBrand.setShowStatus(showStatus);
        PmsBrandExample pmsBrandExample = new PmsBrandExample();
        pmsBrandExample.createCriteria().andIdIn(ids);
        return brandMapper.updateByExampleSelective(pmsBrand, pmsBrandExample);
    }

    @Override
    public int updateFactoryStatus(List<Long> ids, Integer factoryStatus) {
        PmsBrand pmsBrand = new PmsBrand();
        pmsBrand.setFactoryStatus(factoryStatus);
        PmsBrandExample pmsBrandExample = new PmsBrandExample();
        pmsBrandExample.createCriteria().andIdIn(ids);
        return brandMapper.updateByExampleSelective(pmsBrand, pmsBrandExample);
    }
}

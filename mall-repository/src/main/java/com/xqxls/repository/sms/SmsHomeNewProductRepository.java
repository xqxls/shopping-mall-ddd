package com.xqxls.repository.sms;

import com.github.pagehelper.PageHelper;
import com.xqxls.convert.sms.SmsHomeNewProductConvert;
import com.xqxls.mapper.SmsHomeNewProductMapper;
import com.xqxls.model.SmsHomeNewProduct;
import com.xqxls.sms.model.vo.SmsHomeNewProductVO;
import com.xqxls.sms.repository.ISmsHomeNewProductRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/30 17:48
 */
@Repository
public class SmsHomeNewProductRepository implements ISmsHomeNewProductRepository {

    @Resource
    private SmsHomeNewProductMapper smsHomeNewProductMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int create(List<SmsHomeNewProductVO> homeNewProductVOList) {
        List<SmsHomeNewProduct> homeNewProductList = SmsHomeNewProductConvert.INSTANCE.smsHomeNewProductVOToEntityList(homeNewProductVOList);
        for (SmsHomeNewProduct SmsHomeNewProduct : homeNewProductList) {
            SmsHomeNewProduct.setRecommendStatus(1);
            SmsHomeNewProduct.setSort(0);
            smsHomeNewProductMapper.insert(SmsHomeNewProduct);
        }
        return homeNewProductList.size();
    }

    @Override
    public int updateSort(Long id, Integer sort) {
        SmsHomeNewProduct homeNewProduct = new SmsHomeNewProduct();
        homeNewProduct.setId(id);
        homeNewProduct.setSort(sort);
        return smsHomeNewProductMapper.updateByPrimaryKeySelective(homeNewProduct);
    }

    @Override
    public int delete(List<Long> ids) {
        Example example = new Example(SmsHomeNewProduct.class);
        example.createCriteria().andIn("id",ids);
        return smsHomeNewProductMapper.deleteByExample(example);
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        Example example = new Example(SmsHomeNewProduct.class);
        example.createCriteria().andIn("id",ids);
        SmsHomeNewProduct record = new SmsHomeNewProduct();
        record.setRecommendStatus(recommendStatus);
        return smsHomeNewProductMapper.updateByExampleSelective(record,example);
    }

    @Override
    public List<SmsHomeNewProductVO> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        Example example = new Example(SmsHomeNewProduct.class);
        Example.Criteria criteria = example.createCriteria();
        if(StringUtils.hasText(productName)){
            criteria.andLike("productName","%"+productName+"%");
        }
        if(recommendStatus!=null){
            criteria.andEqualTo("recommendStatus",recommendStatus);
        }
        example.setOrderByClause("sort desc");
        return SmsHomeNewProductConvert.INSTANCE.smsHomeNewProductEntityToVOList(smsHomeNewProductMapper.selectByExample(example));
    }
}

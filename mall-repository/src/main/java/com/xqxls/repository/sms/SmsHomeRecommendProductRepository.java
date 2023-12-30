package com.xqxls.repository.sms;

import com.github.pagehelper.PageHelper;
import com.xqxls.convert.sms.SmsHomeRecommendProductConvert;
import com.xqxls.mapper.SmsHomeRecommendProductMapper;
import com.xqxls.model.SmsHomeRecommendProduct;
import com.xqxls.model.SmsHomeRecommendProductExample;
import com.xqxls.sms.model.vo.SmsHomeRecommendProductVO;
import com.xqxls.sms.repository.ISmsHomeRecommendProductRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/30 19:39
 */
@Repository
public class SmsHomeRecommendProductRepository implements ISmsHomeRecommendProductRepository {

    @Resource
    private SmsHomeRecommendProductMapper SmsHomeRecommendProductMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int create(List<SmsHomeRecommendProductVO> homeRecommendProductVOList) {
        List<SmsHomeRecommendProduct> homeRecommendProductList = SmsHomeRecommendProductConvert.INSTANCE.smsHomeRecommendProductVOToEntityList(homeRecommendProductVOList);
        for (SmsHomeRecommendProduct recommendProduct : homeRecommendProductList) {
            recommendProduct.setRecommendStatus(1);
            recommendProduct.setSort(0);
            SmsHomeRecommendProductMapper.insert(recommendProduct);
        }
        return homeRecommendProductList.size();
    }

    @Override
    public int updateSort(Long id, Integer sort) {
        SmsHomeRecommendProduct recommendProduct = new SmsHomeRecommendProduct();
        recommendProduct.setId(id);
        recommendProduct.setSort(sort);
        return SmsHomeRecommendProductMapper.updateByPrimaryKeySelective(recommendProduct);
    }

    @Override
    public int delete(List<Long> ids) {
        SmsHomeRecommendProductExample example = new SmsHomeRecommendProductExample();
        example.createCriteria().andIdIn(ids);
        return SmsHomeRecommendProductMapper.deleteByExample(example);
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        SmsHomeRecommendProductExample example = new SmsHomeRecommendProductExample();
        example.createCriteria().andIdIn(ids);
        SmsHomeRecommendProduct record = new SmsHomeRecommendProduct();
        record.setRecommendStatus(recommendStatus);
        return SmsHomeRecommendProductMapper.updateByExampleSelective(record,example);
    }

    @Override
    public List<SmsHomeRecommendProductVO> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        SmsHomeRecommendProductExample example = new SmsHomeRecommendProductExample();
        SmsHomeRecommendProductExample.Criteria criteria = example.createCriteria();
        if(StringUtils.hasText(productName)){
            criteria.andProductNameLike("%"+productName+"%");
        }
        if(recommendStatus!=null){
            criteria.andRecommendStatusEqualTo(recommendStatus);
        }
        example.setOrderByClause("sort desc");
        return SmsHomeRecommendProductConvert.INSTANCE.smsHomeRecommendProductEntityToVOList(SmsHomeRecommendProductMapper.selectByExample(example));
    }
}

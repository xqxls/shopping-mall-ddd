package com.xqxls.repository.sms;

import com.github.pagehelper.PageHelper;
import com.xqxls.convert.sms.SmsHomeBrandConvert;
import com.xqxls.mapper.SmsHomeBrandMapper;
import com.xqxls.model.SmsHomeBrand;
import com.xqxls.model.SmsHomeBrandExample;
import com.xqxls.sms.model.vo.SmsHomeBrandVO;
import com.xqxls.sms.repository.ISmsHomeBrandRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/30 17:40
 */
@Repository
public class SmsHomeBrandRepository implements ISmsHomeBrandRepository {

    @Resource
    private SmsHomeBrandMapper SmsHomeBrandMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int create(List<SmsHomeBrandVO> homeBrandVOList) {
        List<SmsHomeBrand> homeBrandList = SmsHomeBrandConvert.INSTANCE.smsHomeBrandVOToEntityList(homeBrandVOList);
        for (SmsHomeBrand smsHomeBrand : homeBrandList) {
            smsHomeBrand.setRecommendStatus(1);
            smsHomeBrand.setSort(0);
            SmsHomeBrandMapper.insert(smsHomeBrand);
        }
        return homeBrandList.size();
    }

    @Override
    public int updateSort(Long id, Integer sort) {
        SmsHomeBrand homeBrand = new SmsHomeBrand();
        homeBrand.setId(id);
        homeBrand.setSort(sort);
        return SmsHomeBrandMapper.updateByPrimaryKeySelective(homeBrand);
    }

    @Override
    public int delete(List<Long> ids) {
        SmsHomeBrandExample example = new SmsHomeBrandExample();
        example.createCriteria().andIdIn(ids);
        return SmsHomeBrandMapper.deleteByExample(example);
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        SmsHomeBrandExample example = new SmsHomeBrandExample();
        example.createCriteria().andIdIn(ids);
        SmsHomeBrand record = new SmsHomeBrand();
        record.setRecommendStatus(recommendStatus);
        return SmsHomeBrandMapper.updateByExampleSelective(record,example);
    }

    @Override
    public List<SmsHomeBrandVO> list(String brandName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        SmsHomeBrandExample example = new SmsHomeBrandExample();
        SmsHomeBrandExample.Criteria criteria = example.createCriteria();
        if(StringUtils.hasText(brandName)){
            criteria.andBrandNameLike("%"+brandName+"%");
        }
        if(recommendStatus!=null){
            criteria.andRecommendStatusEqualTo(recommendStatus);
        }
        example.setOrderByClause("sort desc");
        return SmsHomeBrandConvert.INSTANCE.smsHomeBrandEntityToVOList(SmsHomeBrandMapper.selectByExample(example));
    }
}

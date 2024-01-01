package com.xqxls.repository.sms;

import com.github.pagehelper.PageHelper;
import com.xqxls.convert.sms.SmsHomeRecommendSubjectConvert;
import com.xqxls.mapper.SmsHomeRecommendSubjectMapper;
import com.xqxls.model.SmsHomeRecommendSubject;
import com.xqxls.sms.model.vo.SmsHomeRecommendSubjectVO;
import com.xqxls.sms.repository.ISmsHomeRecommendSubjectRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/30 19:45
 */
@Repository
public class SmsHomeRecommendSubjectRepository implements ISmsHomeRecommendSubjectRepository {

    @Resource
    private SmsHomeRecommendSubjectMapper smsHomeRecommendSubjectMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int create(List<SmsHomeRecommendSubjectVO> recommendSubjectVOList) {
        List<SmsHomeRecommendSubject> recommendSubjectList = SmsHomeRecommendSubjectConvert.INSTANCE.smsHomeRecommendSubjectVOToEntityList(recommendSubjectVOList);
        for (SmsHomeRecommendSubject recommendProduct : recommendSubjectList) {
            recommendProduct.setRecommendStatus(1);
            recommendProduct.setSort(0);
            smsHomeRecommendSubjectMapper.insert(recommendProduct);
        }
        return recommendSubjectList.size();
    }

    @Override
    public int updateSort(Long id, Integer sort) {
        SmsHomeRecommendSubject recommendProduct = new SmsHomeRecommendSubject();
        recommendProduct.setId(id);
        recommendProduct.setSort(sort);
        return smsHomeRecommendSubjectMapper.updateByPrimaryKeySelective(recommendProduct);
    }

    @Override
    public int delete(List<Long> ids) {
        Example example = new Example(SmsHomeRecommendSubject.class);
        example.createCriteria().andIn("id",ids);
        return smsHomeRecommendSubjectMapper.deleteByExample(example);
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        Example example = new Example(SmsHomeRecommendSubject.class);
        example.createCriteria().andIn("id",ids);
        SmsHomeRecommendSubject record = new SmsHomeRecommendSubject();
        record.setRecommendStatus(recommendStatus);
        return smsHomeRecommendSubjectMapper.updateByExampleSelective(record,example);
    }

    @Override
    public List<SmsHomeRecommendSubjectVO> list(String subjectName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        Example example = new Example(SmsHomeRecommendSubject.class);
        Example.Criteria criteria = example.createCriteria();
        if(StringUtils.hasText(subjectName)){
            criteria.andLike("subjectName","%"+subjectName+"%");
        }
        if(recommendStatus!=null){
            criteria.andEqualTo("recommendStatus",recommendStatus);
        }
        example.setOrderByClause("sort desc");
        return SmsHomeRecommendSubjectConvert.INSTANCE.smsHomeRecommendSubjectEntityToVOList(smsHomeRecommendSubjectMapper.selectByExample(example));
    }
}

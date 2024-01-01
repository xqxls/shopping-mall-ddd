package com.xqxls.repository.sms;

import com.github.pagehelper.PageHelper;
import com.xqxls.convert.sms.SmsFlashPromotionConvert;
import com.xqxls.mapper.SmsFlashPromotionMapper;
import com.xqxls.model.SmsFlashPromotion;
import com.xqxls.sms.model.vo.SmsFlashPromotionVO;
import com.xqxls.sms.repository.ISmsFlashPromotionRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/30 17:00
 */
@Repository
public class SmsFlashPromotionRepository implements ISmsFlashPromotionRepository {

    @Resource
    private SmsFlashPromotionMapper flashPromotionMapper;

    @Override
    public int create(SmsFlashPromotionVO flashPromotionVO) {
        SmsFlashPromotion flashPromotion = SmsFlashPromotionConvert.INSTANCE.smsFlashPromotionVOToEntity(flashPromotionVO);
        flashPromotion.setCreateTime(new Date());
        return flashPromotionMapper.insert(flashPromotion);
    }

    @Override
    public int update(Long id, SmsFlashPromotionVO flashPromotionVO) {
        SmsFlashPromotion flashPromotion = SmsFlashPromotionConvert.INSTANCE.smsFlashPromotionVOToEntity(flashPromotionVO);
        flashPromotion.setId(id);
        return flashPromotionMapper.updateByPrimaryKey(flashPromotion);
    }

    @Override
    public int delete(Long id) {
        return flashPromotionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateStatus(Long id, Integer status) {
        SmsFlashPromotion flashPromotion = new SmsFlashPromotion();
        flashPromotion.setId(id);
        flashPromotion.setStatus(status);
        return flashPromotionMapper.updateByPrimaryKeySelective(flashPromotion);
    }

    @Override
    public SmsFlashPromotionVO getItem(Long id) {
        return SmsFlashPromotionConvert.INSTANCE.smsFlashPromotionEntityToVO(flashPromotionMapper.selectByPrimaryKey(id));
    }

    @Override
    public List<SmsFlashPromotionVO> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(SmsFlashPromotion.class);
        if (StringUtils.hasText(keyword)) {
            example.createCriteria().andLike("title","%" + keyword + "%");
        }
        return SmsFlashPromotionConvert.INSTANCE.smsFlashPromotionEntityToVOList(flashPromotionMapper.selectByExample(example));
    }
}

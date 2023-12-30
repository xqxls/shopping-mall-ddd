package com.xqxls.sms.service.impl;

import com.xqxls.sms.model.vo.SmsFlashPromotionVO;
import com.xqxls.sms.repository.ISmsFlashPromotionRepository;
import com.xqxls.sms.service.SmsFlashPromotionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 限时购活动管理Service实现类
 * Created by xqxls on 2018/11/16.
 */
@Service
public class SmsFlashPromotionServiceImpl implements SmsFlashPromotionService {
    @Resource
    private ISmsFlashPromotionRepository smsFlashPromotionRepository;

    @Override
    public int create(SmsFlashPromotionVO flashPromotionVO) {
        return smsFlashPromotionRepository.create(flashPromotionVO);
    }

    @Override
    public int update(Long id, SmsFlashPromotionVO flashPromotionVO) {
        return smsFlashPromotionRepository.update(id,flashPromotionVO);
    }

    @Override
    public int delete(Long id) {
        return smsFlashPromotionRepository.delete(id);
    }

    @Override
    public int updateStatus(Long id, Integer status) {
        return smsFlashPromotionRepository.updateStatus(id,status);
    }

    @Override
    public SmsFlashPromotionVO getItem(Long id) {
        return smsFlashPromotionRepository.getItem(id);
    }

    @Override
    public List<SmsFlashPromotionVO> list(String keyword, Integer pageSize, Integer pageNum) {
        return smsFlashPromotionRepository.list(keyword,pageSize,pageNum);
    }
}

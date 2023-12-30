package com.xqxls.sms.service.impl;

import com.xqxls.sms.model.res.SmsFlashPromotionSessionDetailResult;
import com.xqxls.sms.model.vo.SmsFlashPromotionSessionVO;
import com.xqxls.sms.repository.ISmsFlashPromotionSessionRepository;
import com.xqxls.sms.service.SmsFlashPromotionSessionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 限时购场次管理Service实现类
 * Created by xqxls on 2018/11/16.
 */
@Service
public class SmsFlashPromotionSessionServiceImpl implements SmsFlashPromotionSessionService {
    @Resource
    private ISmsFlashPromotionSessionRepository smsFlashPromotionSessionRepository;

    @Override
    public int create(SmsFlashPromotionSessionVO promotionSessionVO) {
        return smsFlashPromotionSessionRepository.create(promotionSessionVO);
    }

    @Override
    public int update(Long id, SmsFlashPromotionSessionVO promotionSessionVO) {
        return smsFlashPromotionSessionRepository.update(id,promotionSessionVO);
    }

    @Override
    public int updateStatus(Long id, Integer status) {
        return smsFlashPromotionSessionRepository.updateStatus(id,status);
    }

    @Override
    public int delete(Long id) {
        return smsFlashPromotionSessionRepository.delete(id);
    }

    @Override
    public SmsFlashPromotionSessionVO getItem(Long id) {
        return smsFlashPromotionSessionRepository.getItem(id);
    }

    @Override
    public List<SmsFlashPromotionSessionVO> list() {
        return smsFlashPromotionSessionRepository.list();
    }

    @Override
    public List<SmsFlashPromotionSessionDetailResult> selectList(Long flashPromotionId) {
        return smsFlashPromotionSessionRepository.selectList(flashPromotionId);
    }
}

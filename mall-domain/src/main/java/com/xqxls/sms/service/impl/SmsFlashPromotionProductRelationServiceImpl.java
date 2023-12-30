package com.xqxls.sms.service.impl;

import com.xqxls.sms.model.res.SmsFlashPromotionProductResult;
import com.xqxls.sms.model.vo.SmsFlashPromotionProductRelationVO;
import com.xqxls.sms.repository.ISmsFlashPromotionProductRelationRepository;
import com.xqxls.sms.service.SmsFlashPromotionProductRelationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 限时购商品关联管理Service实现类
 * Created by xqxls on 2018/11/16.
 */
@Service
public class SmsFlashPromotionProductRelationServiceImpl implements SmsFlashPromotionProductRelationService {
    @Resource
    private ISmsFlashPromotionProductRelationRepository smsFlashPromotionProductRelationRepository;

    @Override
    public int create(List<SmsFlashPromotionProductRelationVO> relationList) {
        return smsFlashPromotionProductRelationRepository.create(relationList);
    }

    @Override
    public int update(Long id, SmsFlashPromotionProductRelationVO relation) {
        return smsFlashPromotionProductRelationRepository.update(id,relation);
    }

    @Override
    public int delete(Long id) {
        return smsFlashPromotionProductRelationRepository.delete(id);
    }

    @Override
    public SmsFlashPromotionProductRelationVO getItem(Long id) {
        return smsFlashPromotionProductRelationRepository.getItem(id);
    }

    @Override
    public List<SmsFlashPromotionProductResult> list(Long flashPromotionId, Long flashPromotionSessionId, Integer pageSize, Integer pageNum) {
        return smsFlashPromotionProductRelationRepository.list(flashPromotionId,flashPromotionSessionId,pageSize,pageNum);
    }

    @Override
    public long getCount(Long flashPromotionId, Long flashPromotionSessionId) {
        return smsFlashPromotionProductRelationRepository.getCount(flashPromotionId,flashPromotionSessionId);
    }
}

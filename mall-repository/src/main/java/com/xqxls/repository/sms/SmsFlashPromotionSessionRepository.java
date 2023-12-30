package com.xqxls.repository.sms;

import com.xqxls.convert.sms.SmsFlashPromotionSessionConvert;
import com.xqxls.mapper.SmsFlashPromotionSessionMapper;
import com.xqxls.model.SmsFlashPromotionSession;
import com.xqxls.model.SmsFlashPromotionSessionExample;
import com.xqxls.sms.model.res.SmsFlashPromotionSessionDetailResult;
import com.xqxls.sms.model.vo.SmsFlashPromotionSessionVO;
import com.xqxls.sms.repository.ISmsFlashPromotionSessionRepository;
import com.xqxls.sms.service.SmsFlashPromotionProductRelationService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/30 17:14
 */
@Repository
public class SmsFlashPromotionSessionRepository implements ISmsFlashPromotionSessionRepository {

    @Resource
    private SmsFlashPromotionSessionMapper promotionSessionMapper;
    @Resource
    private SmsFlashPromotionProductRelationService relationService;

    @Override
    public int create(SmsFlashPromotionSessionVO promotionSessionVO) {
        SmsFlashPromotionSession promotionSession = SmsFlashPromotionSessionConvert.INSTANCE.smsFlashPromotionSessionVOToEntity(promotionSessionVO);
        promotionSession.setCreateTime(new Date());
        return promotionSessionMapper.insert(promotionSession);
    }

    @Override
    public int update(Long id, SmsFlashPromotionSessionVO promotionSessionVO) {
        SmsFlashPromotionSession promotionSession = SmsFlashPromotionSessionConvert.INSTANCE.smsFlashPromotionSessionVOToEntity(promotionSessionVO);
        promotionSession.setId(id);
        return promotionSessionMapper.updateByPrimaryKey(promotionSession);
    }

    @Override
    public int updateStatus(Long id, Integer status) {
        SmsFlashPromotionSession promotionSession = new SmsFlashPromotionSession();
        promotionSession.setId(id);
        promotionSession.setStatus(status);
        return promotionSessionMapper.updateByPrimaryKeySelective(promotionSession);
    }

    @Override
    public int delete(Long id) {
        return promotionSessionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public SmsFlashPromotionSessionVO getItem(Long id) {
        return SmsFlashPromotionSessionConvert.INSTANCE.smsFlashPromotionSessionEntityToVO(promotionSessionMapper.selectByPrimaryKey(id));
    }

    @Override
    public List<SmsFlashPromotionSessionVO> list() {
        SmsFlashPromotionSessionExample example = new SmsFlashPromotionSessionExample();
        return SmsFlashPromotionSessionConvert.INSTANCE.smsFlashPromotionSessionEntityToVOList(promotionSessionMapper.selectByExample(example));
    }

    @Override
    public List<SmsFlashPromotionSessionDetailResult> selectList(Long flashPromotionId) {
        SmsFlashPromotionSessionExample example = new SmsFlashPromotionSessionExample();
        example.createCriteria().andStatusEqualTo(1);
        List<SmsFlashPromotionSession> list = promotionSessionMapper.selectByExample(example);
        List<SmsFlashPromotionSessionDetailResult> resultList = new ArrayList<>();
        for (SmsFlashPromotionSession promotionSession : list) {
            long count = relationService.getCount(flashPromotionId, promotionSession.getId());
            SmsFlashPromotionSessionDetailResult result = new SmsFlashPromotionSessionDetailResult();
            BeanUtils.copyProperties(promotionSession,result);
            result.setProductCount(count);
            resultList.add(result);
        }
        return resultList;
    }
}

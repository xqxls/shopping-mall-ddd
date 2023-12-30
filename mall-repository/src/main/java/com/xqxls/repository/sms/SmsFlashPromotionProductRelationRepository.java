package com.xqxls.repository.sms;

import com.github.pagehelper.PageHelper;
import com.xqxls.convert.sms.SmsFlashPromotionProductRelationConvert;
import com.xqxls.dao.SmsFlashPromotionProductRelationDao;
import com.xqxls.dto.SmsFlashPromotionProduct;
import com.xqxls.mapper.SmsFlashPromotionProductRelationMapper;
import com.xqxls.model.SmsFlashPromotionProductRelation;
import com.xqxls.model.SmsFlashPromotionProductRelationExample;
import com.xqxls.pms.model.vo.PmsProductVO;
import com.xqxls.sms.model.res.SmsFlashPromotionProductResult;
import com.xqxls.sms.model.vo.SmsFlashPromotionProductRelationVO;
import com.xqxls.sms.repository.ISmsFlashPromotionProductRelationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/30 16:36
 */
@Repository
public class SmsFlashPromotionProductRelationRepository implements ISmsFlashPromotionProductRelationRepository {

    @Resource
    private SmsFlashPromotionProductRelationMapper relationMapper;
    @Resource
    private SmsFlashPromotionProductRelationDao relationDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int create(List<SmsFlashPromotionProductRelationVO> relationList) {
        List<SmsFlashPromotionProductRelation> smsFlashPromotionProductRelationList = SmsFlashPromotionProductRelationConvert.INSTANCE.smsFlashPromotionProductRelationVOToEntityList(relationList);
        for (SmsFlashPromotionProductRelation relation : smsFlashPromotionProductRelationList) {
            relationMapper.insert(relation);
        }
        return relationList.size();
    }

    @Override
    public int update(Long id, SmsFlashPromotionProductRelationVO relation) {
        SmsFlashPromotionProductRelation smsFlashPromotionProductRelation = SmsFlashPromotionProductRelationConvert.INSTANCE.smsFlashPromotionProductRelationVOToEntity(relation);
        relation.setId(id);
        return relationMapper.updateByPrimaryKey(smsFlashPromotionProductRelation);
    }

    @Override
    public int delete(Long id) {
        return relationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public SmsFlashPromotionProductRelationVO getItem(Long id) {
        return SmsFlashPromotionProductRelationConvert.INSTANCE.smsFlashPromotionProductRelationEntityToVO(relationMapper.selectByPrimaryKey(id));
    }

    @Override
    public List<SmsFlashPromotionProductResult> list(Long flashPromotionId, Long flashPromotionSessionId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        List<SmsFlashPromotionProduct> smsFlashPromotionProductList = relationDao.getList(flashPromotionId,flashPromotionSessionId);
        List<SmsFlashPromotionProductResult> results = new ArrayList<>();

        for(SmsFlashPromotionProduct smsFlashPromotionProduct : smsFlashPromotionProductList){
            SmsFlashPromotionProductResult result = new SmsFlashPromotionProductResult();
            PmsProductVO productVO = new PmsProductVO();
            BeanUtils.copyProperties(smsFlashPromotionProduct,result);
            BeanUtils.copyProperties(smsFlashPromotionProduct.getProduct(),productVO);
            result.setProduct(productVO);
            results.add(result);
        }
        return results;
    }

    @Override
    public long getCount(Long flashPromotionId, Long flashPromotionSessionId) {
        SmsFlashPromotionProductRelationExample example = new SmsFlashPromotionProductRelationExample();
        example.createCriteria()
                .andFlashPromotionIdEqualTo(flashPromotionId)
                .andFlashPromotionSessionIdEqualTo(flashPromotionSessionId);
        return relationMapper.countByExample(example);
    }
}

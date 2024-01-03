package com.xqxls.service.impl;

import com.github.pagehelper.PageHelper;
import com.xqxls.mapper.*;
import com.xqxls.model.*;
import com.xqxls.dao.HomeDao;
import com.xqxls.dto.FlashPromotionProduct;
import com.xqxls.dto.HomeContentResult;
import com.xqxls.dto.HomeFlashPromotion;
import com.xqxls.service.HomeService;
import com.xqxls.util.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 首页内容管理Service实现类
 * Created by macro on 2019/1/28.
 */
@Service
public class HomeServiceImpl implements HomeService {
    @Resource
    private SmsHomeAdvertiseMapper advertiseMapper;
    @Resource
    private HomeDao homeDao;
    @Resource
    private SmsFlashPromotionMapper flashPromotionMapper;
    @Resource
    private SmsFlashPromotionSessionMapper promotionSessionMapper;
    @Resource
    private PmsProductMapper productMapper;
    @Resource
    private PmsProductCategoryMapper productCategoryMapper;
    @Resource
    private CmsSubjectMapper subjectMapper;

    @Override
    public HomeContentResult content() {
        HomeContentResult result = new HomeContentResult();
        //获取首页广告
        result.setAdvertiseList(getHomeAdvertiseList());
        //获取推荐品牌
        result.setBrandList(homeDao.getRecommendBrandList(0,6));
        //获取秒杀信息
        result.setHomeFlashPromotion(getHomeFlashPromotion());
        //获取新品推荐
        result.setNewProductList(homeDao.getNewProductList(0,4));
        //获取人气推荐
        result.setHotProductList(homeDao.getHotProductList(0,4));
        //获取推荐专题
        result.setSubjectList(homeDao.getRecommendSubjectList(0,4));
        return result;
    }

    @Override
    public List<PmsProduct> recommendProductList(Integer pageSize, Integer pageNum) {
        // TODO: 2019/1/29 暂时默认推荐所有商品
        PageHelper.startPage(pageNum,pageSize);
        Example example = new Example(PmsProduct.class);
        example.createCriteria()
                .andEqualTo("deleteStatus",0)
                .andEqualTo("publishStatus",1);
        return productMapper.selectByExample(example);
    }

    @Override
    public List<PmsProductCategory> getProductCateList(Long parentId) {
        Example example = new Example(PmsProductCategory.class);
        example.createCriteria()
                .andEqualTo("showStatus",1)
                .andEqualTo("parentId",parentId);
        example.setOrderByClause("sort desc");
        return productCategoryMapper.selectByExample(example);
    }

    @Override
    public List<CmsSubject> getSubjectList(Long cateId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        Example example = new Example(CmsSubject.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("showStatus",1);
        if(cateId!=null){
            criteria.andEqualTo("categoryId",cateId);
        }
        return subjectMapper.selectByExample(example);
    }

    @Override
    public List<PmsProduct> hotProductList(Integer pageNum, Integer pageSize) {
        int offset = pageSize * (pageNum - 1);
        return homeDao.getHotProductList(offset, pageSize);
    }

    @Override
    public List<PmsProduct> newProductList(Integer pageNum, Integer pageSize) {
        int offset = pageSize * (pageNum - 1);
        return homeDao.getNewProductList(offset, pageSize);
    }

    private HomeFlashPromotion getHomeFlashPromotion() {
        HomeFlashPromotion homeFlashPromotion = new HomeFlashPromotion();
        //获取当前秒杀活动
        Date now = new Date();
        SmsFlashPromotion flashPromotion = getFlashPromotion(now);
        if (flashPromotion != null) {
            //获取当前秒杀场次
            SmsFlashPromotionSession flashPromotionSession = getFlashPromotionSession(now);
            if (flashPromotionSession != null) {
                homeFlashPromotion.setStartTime(flashPromotionSession.getStartTime());
                homeFlashPromotion.setEndTime(flashPromotionSession.getEndTime());
                //获取下一个秒杀场次
                SmsFlashPromotionSession nextSession = getNextFlashPromotionSession(homeFlashPromotion.getStartTime());
                if(nextSession!=null){
                    homeFlashPromotion.setNextStartTime(nextSession.getStartTime());
                    homeFlashPromotion.setNextEndTime(nextSession.getEndTime());
                }
                //获取秒杀商品
                List<FlashPromotionProduct> flashProductList = homeDao.getFlashProductList(flashPromotion.getId(), flashPromotionSession.getId());
                homeFlashPromotion.setProductList(flashProductList);
            }
        }
        return homeFlashPromotion;
    }

    //获取下一个场次信息
    private SmsFlashPromotionSession getNextFlashPromotionSession(Date date) {
        Example sessionExample = new Example(SmsFlashPromotionSession.class);
        sessionExample.createCriteria()
                .andGreaterThan("startTime",date);
        sessionExample.setOrderByClause("start_time asc");
        List<SmsFlashPromotionSession> promotionSessionList = promotionSessionMapper.selectByExample(sessionExample);
        if (!CollectionUtils.isEmpty(promotionSessionList)) {
            return promotionSessionList.get(0);
        }
        return null;
    }

    private List<SmsHomeAdvertise> getHomeAdvertiseList() {
        Example example = new Example(SmsHomeAdvertise.class);
        example.createCriteria().andEqualTo("type",1).andEqualTo("status",1);
        example.setOrderByClause("sort desc");
        return advertiseMapper.selectByExample(example);
    }

    //根据时间获取秒杀活动
    private SmsFlashPromotion getFlashPromotion(Date date) {
        Date currDate = DateUtil.getDate(date);
        Example example = new Example(SmsFlashPromotion.class);
        example.createCriteria()
                .andEqualTo("status",1)
                .andLessThanOrEqualTo("startDate",currDate)
                .andGreaterThanOrEqualTo("endDate",currDate);
        List<SmsFlashPromotion> flashPromotionList = flashPromotionMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(flashPromotionList)) {
            return flashPromotionList.get(0);
        }
        return null;
    }

    //根据时间获取秒杀场次
    private SmsFlashPromotionSession getFlashPromotionSession(Date date) {
        Date currTime = DateUtil.getTime(date);
        Example sessionExample = new Example(SmsFlashPromotionSession.class);
        sessionExample.createCriteria()
                .andLessThanOrEqualTo("startTime",currTime)
                .andGreaterThanOrEqualTo("endTime",currTime);
        List<SmsFlashPromotionSession> promotionSessionList = promotionSessionMapper.selectByExample(sessionExample);
        if (!CollectionUtils.isEmpty(promotionSessionList)) {
            return promotionSessionList.get(0);
        }
        return null;
    }
}

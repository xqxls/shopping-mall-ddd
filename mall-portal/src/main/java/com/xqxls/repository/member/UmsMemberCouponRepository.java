package com.xqxls.repository.member;

import cn.hutool.core.collection.CollUtil;
import com.xqxls.convert.member.UmsMemberCouponConvert;
import com.xqxls.dao.SmsCouponHistoryDao;
import com.xqxls.domain.member.model.aggregates.CartPromotionItemRich;
import com.xqxls.domain.member.model.res.SmsCouponHistoryDetailResult;
import com.xqxls.domain.member.model.vo.SmsCouponHistoryVO;
import com.xqxls.domain.member.model.vo.SmsCouponProductCategoryRelationVO;
import com.xqxls.domain.member.model.vo.SmsCouponProductRelationVO;
import com.xqxls.domain.member.model.vo.SmsCouponVO;
import com.xqxls.domain.member.repository.IUmsMemberCouponRepository;
import com.xqxls.dto.CartPromotionItem;
import com.xqxls.dto.SmsCouponHistoryDetail;
import com.xqxls.exception.Asserts;
import com.xqxls.mapper.*;
import com.xqxls.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/2 17:02
 */
@Repository
public class UmsMemberCouponRepository implements IUmsMemberCouponRepository {

    @Resource
    private UmsMemberRepository umsMemberRepository;
    @Resource
    private SmsCouponMapper couponMapper;
    @Resource
    private SmsCouponHistoryMapper couponHistoryMapper;
    @Resource
    private SmsCouponHistoryDao couponHistoryDao;
    @Resource
    private SmsCouponProductRelationMapper couponProductRelationMapper;
    @Resource
    private SmsCouponProductCategoryRelationMapper couponProductCategoryRelationMapper;
    @Resource
    private PmsProductMapper productMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(Long couponId) {
        UmsMember currentMember = umsMemberRepository.getCurrentMember();
        //获取优惠券信息，判断数量
        SmsCoupon coupon = couponMapper.selectByPrimaryKey(couponId);
        if(coupon==null){
            Asserts.fail("优惠券不存在");
        }
        if(coupon.getCount()<=0){
            Asserts.fail("优惠券已经领完了");
        }
        Date now = new Date();
        if(now.before(coupon.getEnableTime())){
            Asserts.fail("优惠券还没到领取时间");
        }
        //判断用户领取的优惠券数量是否超过限制
        Example couponHistoryExample = new Example(SmsCouponHistory.class);
        couponHistoryExample.createCriteria().andEqualTo("couponId",couponId).andEqualTo("memberId",currentMember.getId());
        long count = couponHistoryMapper.selectCountByExample(couponHistoryExample);
        if(count>=coupon.getPerLimit()){
            Asserts.fail("您已经领取过该优惠券");
        }
        //生成领取优惠券历史
        SmsCouponHistory couponHistory = new SmsCouponHistory();
        couponHistory.setCouponId(couponId);
        couponHistory.setCouponCode(generateCouponCode(currentMember.getId()));
        couponHistory.setCreateTime(now);
        couponHistory.setMemberId(currentMember.getId());
        couponHistory.setMemberNickname(currentMember.getNickname());
        //主动领取
        couponHistory.setGetType(1);
        //未使用
        couponHistory.setUseStatus(0);
        couponHistoryMapper.insert(couponHistory);
        //修改优惠券表的数量、领取数量
        coupon.setCount(coupon.getCount()-1);
        coupon.setReceiveCount(coupon.getReceiveCount()==null?1:coupon.getReceiveCount()+1);
        couponMapper.updateByPrimaryKey(coupon);
    }

    /**
     * 16位优惠码生成：时间戳后8位+4位随机数+用户id后4位
     */
    private String generateCouponCode(Long memberId) {
        StringBuilder sb = new StringBuilder();
        Long currentTimeMillis = System.currentTimeMillis();
        String timeMillisStr = currentTimeMillis.toString();
        sb.append(timeMillisStr.substring(timeMillisStr.length() - 8));
        for (int i = 0; i < 4; i++) {
            sb.append(new Random().nextInt(10));
        }
        String memberIdStr = memberId.toString();
        if (memberIdStr.length() <= 4) {
            sb.append(String.format("%04d", memberId));
        } else {
            sb.append(memberIdStr.substring(memberIdStr.length()-4));
        }
        return sb.toString();
    }

    @Override
    public List<SmsCouponHistoryVO> listHistory(Integer useStatus) {
        UmsMember currentMember = umsMemberRepository.getCurrentMember();
        Example couponHistoryExample=new Example(SmsCouponHistory.class);
        Example.Criteria criteria = couponHistoryExample.createCriteria();
        criteria.andEqualTo("memberId",currentMember.getId());
        if(useStatus!=null){
            criteria.andEqualTo("useStatus",useStatus);
        }
        return UmsMemberCouponConvert.INSTANCE.smsCouponHistoryEntityToVOList(couponHistoryMapper.selectByExample(couponHistoryExample));
    }

    @Override
    public List<SmsCouponHistoryDetailResult> listCart(List<CartPromotionItemRich> cartPromotionItemRichList, Integer type) {
        List<CartPromotionItem> cartItemList =new ArrayList<>();
        for(CartPromotionItemRich rich:cartPromotionItemRichList){
            CartPromotionItem cartPromotionItem = new CartPromotionItem();
            BeanUtils.copyProperties(rich,cartPromotionItem);
            BeanUtils.copyProperties(rich.getOmsCartItem(),cartPromotionItem);
            cartItemList.add(cartPromotionItem);
        }
        UmsMember currentMember = umsMemberRepository.getCurrentMember();
        Date now = new Date();
        //获取该用户所有优惠券
        List<SmsCouponHistoryDetail> allList = couponHistoryDao.getDetailList(currentMember.getId());
        //根据优惠券使用类型来判断优惠券是否可用
        List<SmsCouponHistoryDetail> enableList = new ArrayList<>();
        List<SmsCouponHistoryDetail> disableList = new ArrayList<>();
        for (SmsCouponHistoryDetail couponHistoryDetail : allList) {
            Integer useType = couponHistoryDetail.getCoupon().getUseType();
            BigDecimal minPoint = couponHistoryDetail.getCoupon().getMinPoint();
            Date endTime = couponHistoryDetail.getCoupon().getEndTime();
            if(useType.equals(0)){
                //0->全场通用
                //判断是否满足优惠起点
                //计算购物车商品的总价
                BigDecimal totalAmount = calcTotalAmount(cartItemList);
                if(now.before(endTime)&&totalAmount.subtract(minPoint).intValue()>=0){
                    enableList.add(couponHistoryDetail);
                }else{
                    disableList.add(couponHistoryDetail);
                }
            }else if(useType.equals(1)){
                //1->指定分类
                //计算指定分类商品的总价
                List<Long> productCategoryIds = new ArrayList<>();
                for (SmsCouponProductCategoryRelation categoryRelation : couponHistoryDetail.getCategoryRelationList()) {
                    productCategoryIds.add(categoryRelation.getProductCategoryId());
                }
                BigDecimal totalAmount = calcTotalAmountByproductCategoryId(cartItemList,productCategoryIds);
                if(now.before(endTime)&&totalAmount.intValue()>0&&totalAmount.subtract(minPoint).intValue()>=0){
                    enableList.add(couponHistoryDetail);
                }else{
                    disableList.add(couponHistoryDetail);
                }
            }else if(useType.equals(2)){
                //2->指定商品
                //计算指定商品的总价
                List<Long> productIds = new ArrayList<>();
                for (SmsCouponProductRelation productRelation : couponHistoryDetail.getProductRelationList()) {
                    productIds.add(productRelation.getProductId());
                }
                BigDecimal totalAmount = calcTotalAmountByProductId(cartItemList,productIds);
                if(now.before(endTime)&&totalAmount.intValue()>0&&totalAmount.subtract(minPoint).intValue()>=0){
                    enableList.add(couponHistoryDetail);
                }else{
                    disableList.add(couponHistoryDetail);
                }
            }
        }
        if(type.equals(1)){
            return convertDetailToResult(enableList);
        }else{
            return convertDetailToResult(disableList);
        }
    }

    @Override
    public List<SmsCouponHistoryDetailResult> listCartResult(List<CartPromotionItem> cartPromotionItemList, Integer type) {
        UmsMember currentMember = umsMemberRepository.getCurrentMember();
        Date now = new Date();
        //获取该用户所有优惠券
        List<SmsCouponHistoryDetail> allList = couponHistoryDao.getDetailList(currentMember.getId());
        //根据优惠券使用类型来判断优惠券是否可用
        List<SmsCouponHistoryDetail> enableList = new ArrayList<>();
        List<SmsCouponHistoryDetail> disableList = new ArrayList<>();
        for (SmsCouponHistoryDetail couponHistoryDetail : allList) {
            Integer useType = couponHistoryDetail.getCoupon().getUseType();
            BigDecimal minPoint = couponHistoryDetail.getCoupon().getMinPoint();
            Date endTime = couponHistoryDetail.getCoupon().getEndTime();
            if(useType.equals(0)){
                //0->全场通用
                //判断是否满足优惠起点
                //计算购物车商品的总价
                BigDecimal totalAmount = calcTotalAmount(cartPromotionItemList);
                if(now.before(endTime)&&totalAmount.subtract(minPoint).intValue()>=0){
                    enableList.add(couponHistoryDetail);
                }else{
                    disableList.add(couponHistoryDetail);
                }
            }else if(useType.equals(1)){
                //1->指定分类
                //计算指定分类商品的总价
                List<Long> productCategoryIds = new ArrayList<>();
                for (SmsCouponProductCategoryRelation categoryRelation : couponHistoryDetail.getCategoryRelationList()) {
                    productCategoryIds.add(categoryRelation.getProductCategoryId());
                }
                BigDecimal totalAmount = calcTotalAmountByproductCategoryId(cartPromotionItemList,productCategoryIds);
                if(now.before(endTime)&&totalAmount.intValue()>0&&totalAmount.subtract(minPoint).intValue()>=0){
                    enableList.add(couponHistoryDetail);
                }else{
                    disableList.add(couponHistoryDetail);
                }
            }else if(useType.equals(2)){
                //2->指定商品
                //计算指定商品的总价
                List<Long> productIds = new ArrayList<>();
                for (SmsCouponProductRelation productRelation : couponHistoryDetail.getProductRelationList()) {
                    productIds.add(productRelation.getProductId());
                }
                BigDecimal totalAmount = calcTotalAmountByProductId(cartPromotionItemList,productIds);
                if(now.before(endTime)&&totalAmount.intValue()>0&&totalAmount.subtract(minPoint).intValue()>=0){
                    enableList.add(couponHistoryDetail);
                }else{
                    disableList.add(couponHistoryDetail);
                }
            }
        }
        if(type.equals(1)){
            return convertDetailToResult(enableList);
        }else{
            return convertDetailToResult(disableList);
        }
    }

    @Override
    public List<SmsCouponHistoryDetail> listCartDetail(List<CartPromotionItem> cartPromotionItemList, Integer type) {
        UmsMember currentMember = umsMemberRepository.getCurrentMember();
        Date now = new Date();
        //获取该用户所有优惠券
        List<SmsCouponHistoryDetail> allList = couponHistoryDao.getDetailList(currentMember.getId());
        //根据优惠券使用类型来判断优惠券是否可用
        List<SmsCouponHistoryDetail> enableList = new ArrayList<>();
        List<SmsCouponHistoryDetail> disableList = new ArrayList<>();
        for (SmsCouponHistoryDetail couponHistoryDetail : allList) {
            Integer useType = couponHistoryDetail.getCoupon().getUseType();
            BigDecimal minPoint = couponHistoryDetail.getCoupon().getMinPoint();
            Date endTime = couponHistoryDetail.getCoupon().getEndTime();
            if(useType.equals(0)){
                //0->全场通用
                //判断是否满足优惠起点
                //计算购物车商品的总价
                BigDecimal totalAmount = calcTotalAmount(cartPromotionItemList);
                if(now.before(endTime)&&totalAmount.subtract(minPoint).intValue()>=0){
                    enableList.add(couponHistoryDetail);
                }else{
                    disableList.add(couponHistoryDetail);
                }
            }else if(useType.equals(1)){
                //1->指定分类
                //计算指定分类商品的总价
                List<Long> productCategoryIds = new ArrayList<>();
                for (SmsCouponProductCategoryRelation categoryRelation : couponHistoryDetail.getCategoryRelationList()) {
                    productCategoryIds.add(categoryRelation.getProductCategoryId());
                }
                BigDecimal totalAmount = calcTotalAmountByproductCategoryId(cartPromotionItemList,productCategoryIds);
                if(now.before(endTime)&&totalAmount.intValue()>0&&totalAmount.subtract(minPoint).intValue()>=0){
                    enableList.add(couponHistoryDetail);
                }else{
                    disableList.add(couponHistoryDetail);
                }
            }else if(useType.equals(2)){
                //2->指定商品
                //计算指定商品的总价
                List<Long> productIds = new ArrayList<>();
                for (SmsCouponProductRelation productRelation : couponHistoryDetail.getProductRelationList()) {
                    productIds.add(productRelation.getProductId());
                }
                BigDecimal totalAmount = calcTotalAmountByProductId(cartPromotionItemList,productIds);
                if(now.before(endTime)&&totalAmount.intValue()>0&&totalAmount.subtract(minPoint).intValue()>=0){
                    enableList.add(couponHistoryDetail);
                }else{
                    disableList.add(couponHistoryDetail);
                }
            }
        }
        if(type.equals(1)){
            return enableList;
        }else{
            return disableList;
        }
    }

    private List<SmsCouponHistoryDetailResult> convertDetailToResult(List<SmsCouponHistoryDetail> list){
        List<SmsCouponHistoryDetailResult> results = new ArrayList<>();
        for(SmsCouponHistoryDetail detail:list){
            SmsCouponHistoryDetailResult result = new SmsCouponHistoryDetailResult();
            BeanUtils.copyProperties(detail,result);
            SmsCouponVO smsCouponVO = new SmsCouponVO();
            List<SmsCouponProductRelationVO> productRelationList = new ArrayList<>();
            List<SmsCouponProductCategoryRelationVO> categoryRelationList = new ArrayList<>();
            BeanUtils.copyProperties(detail.getCoupon(),smsCouponVO);
            for(SmsCouponProductRelation smsCouponProductRelation:detail.getProductRelationList()){
                SmsCouponProductRelationVO vo = new SmsCouponProductRelationVO();
                BeanUtils.copyProperties(smsCouponProductRelation,vo);
                productRelationList.add(vo);
            }
            for(SmsCouponProductCategoryRelation smsCouponProductCategoryRelation:detail.getCategoryRelationList()){
                SmsCouponProductCategoryRelationVO vo = new SmsCouponProductCategoryRelationVO();
                BeanUtils.copyProperties(smsCouponProductCategoryRelation,vo);
                categoryRelationList.add(vo);
            }
            result.setCoupon(smsCouponVO);
            result.setProductRelationList(productRelationList);
            result.setCategoryRelationList(categoryRelationList);
            results.add(result);
        }
        return results;
    }

    private BigDecimal calcTotalAmount(List<CartPromotionItem> cartItemList) {
        BigDecimal total = new BigDecimal("0");
        for (CartPromotionItem item : cartItemList) {
            BigDecimal realPrice = item.getPrice().subtract(item.getReduceAmount());
            total=total.add(realPrice.multiply(new BigDecimal(item.getQuantity())));
        }
        return total;
    }

    private BigDecimal calcTotalAmountByproductCategoryId(List<CartPromotionItem> cartItemList,List<Long> productCategoryIds) {
        BigDecimal total = new BigDecimal("0");
        for (CartPromotionItem item : cartItemList) {
            if(productCategoryIds.contains(item.getProductCategoryId())){
                BigDecimal realPrice = item.getPrice().subtract(item.getReduceAmount());
                total=total.add(realPrice.multiply(new BigDecimal(item.getQuantity())));
            }
        }
        return total;
    }

    private BigDecimal calcTotalAmountByProductId(List<CartPromotionItem> cartItemList,List<Long> productIds) {
        BigDecimal total = new BigDecimal("0");
        for (CartPromotionItem item : cartItemList) {
            if(productIds.contains(item.getProductId())){
                BigDecimal realPrice = item.getPrice().subtract(item.getReduceAmount());
                total=total.add(realPrice.multiply(new BigDecimal(item.getQuantity())));
            }
        }
        return total;
    }

    @Override
    public List<SmsCouponVO> listByProduct(Long productId) {
        List<Long> allCouponIds = new ArrayList<>();
        //获取指定商品优惠券
        Example cprExample = new Example(SmsCouponProductRelation.class);
        cprExample.createCriteria().andEqualTo("productId",productId);
        List<SmsCouponProductRelation> cprList = couponProductRelationMapper.selectByExample(cprExample);
        if(CollUtil.isNotEmpty(cprList)){
            List<Long> couponIds = cprList.stream().map(SmsCouponProductRelation::getCouponId).collect(Collectors.toList());
            allCouponIds.addAll(couponIds);
        }
        //获取指定分类优惠券
        PmsProduct product = productMapper.selectByPrimaryKey(productId);
        Example cpcrExample = new Example(SmsCouponProductCategoryRelation.class);
        cpcrExample.createCriteria().andEqualTo("productCategoryId",product.getProductCategoryId());
        List<SmsCouponProductCategoryRelation> cpcrList = couponProductCategoryRelationMapper.selectByExample(cpcrExample);
        if(CollUtil.isNotEmpty(cpcrList)){
            List<Long> couponIds = cpcrList.stream().map(SmsCouponProductCategoryRelation::getCouponId).collect(Collectors.toList());
            allCouponIds.addAll(couponIds);
        }
        if(CollUtil.isEmpty(allCouponIds)){
            return new ArrayList<>();
        }
        //所有优惠券
        Example couponExample = new Example(SmsCoupon.class);
        couponExample.createCriteria().andGreaterThan("endTime",new Date())
                .andLessThan("startTime",new Date())
                .andEqualTo("useType",0);
        couponExample.or(couponExample.createCriteria()
                .andGreaterThan("endTime",new Date())
                .andLessThan("startTime",new Date())
                .andNotEqualTo("useType",0)
                .andIn("id",allCouponIds));
        return UmsMemberCouponConvert.INSTANCE.smsCouponEntityToVOList(couponMapper.selectByExample(couponExample));
    }

    @Override
    public List<SmsCouponVO> list(Integer useStatus) {
        UmsMember member = umsMemberRepository.getCurrentMember();
        return UmsMemberCouponConvert.INSTANCE.smsCouponEntityToVOList(couponHistoryDao.getCouponList(member.getId(),useStatus));
    }
}

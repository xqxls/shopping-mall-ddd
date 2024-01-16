package com.xqxls.domain.member.service.impl;

import com.xqxls.domain.member.model.aggregates.CartPromotionItemRich;
import com.xqxls.domain.member.model.res.SmsCouponHistoryDetailResult;
import com.xqxls.domain.member.model.vo.SmsCouponHistoryVO;
import com.xqxls.domain.member.model.vo.SmsCouponVO;
import com.xqxls.domain.member.repository.IUmsMemberCouponRepository;
import com.xqxls.domain.member.service.UmsMemberCouponService;
import com.xqxls.dto.CartPromotionItem;
import com.xqxls.dto.SmsCouponHistoryDetail;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 会员优惠券管理Service实现类
 * Created by macro on 2018/8/29.
 */
@Service
public class UmsMemberCouponServiceImpl implements UmsMemberCouponService {
    @Resource
    private IUmsMemberCouponRepository umsMemberCouponRepository;

    @Override
    public void add(Long couponId) {
        umsMemberCouponRepository.add(couponId);
    }

    @Override
    public List<SmsCouponHistoryVO> listHistory(Integer useStatus) {
        return umsMemberCouponRepository.listHistory(useStatus);
    }

    @Override
    public List<SmsCouponHistoryDetailResult> listCart(List<CartPromotionItemRich> cartPromotionItemRichList, Integer type) {
        return umsMemberCouponRepository.listCart(cartPromotionItemRichList,type);
    }

    @Override
    public List<SmsCouponHistoryDetailResult> listCartResult(List<CartPromotionItem> cartPromotionItemList, Integer type) {
        return umsMemberCouponRepository.listCartResult(cartPromotionItemList,type);
    }

    @Override
    public List<SmsCouponVO> listByProduct(Long productId) {
        return umsMemberCouponRepository.listByProduct(productId);
    }

    @Override
    public List<SmsCouponVO> list(Integer useStatus) {
        return umsMemberCouponRepository.list(useStatus);
    }

    @Override
    public List<SmsCouponHistoryDetail> listCartDetail(List<CartPromotionItem> cartPromotionItemList, Integer type) {
        return umsMemberCouponRepository.listCartDetail(cartPromotionItemList,type);
    }
}

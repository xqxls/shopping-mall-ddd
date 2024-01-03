package com.xqxls.domain.order.service.impl;

import com.xqxls.domain.order.model.res.CartProductResult;
import com.xqxls.domain.order.model.res.CartPromotionItemResult;
import com.xqxls.domain.order.model.vo.OmsCartItemVO;
import com.xqxls.domain.order.repository.IOmsCartItemRepository;
import com.xqxls.dto.CartPromotionItem;
import com.xqxls.model.OmsCartItem;
import com.xqxls.domain.order.service.OmsCartItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 购物车管理Service实现类
 * Created by macro on 2018/8/2.
 */
@Service
public class OmsCartItemServiceImpl implements OmsCartItemService {
    @Resource
    private IOmsCartItemRepository omsCartItemRepository;

    @Override
    public int add(OmsCartItemVO cartItemVO) {
        return omsCartItemRepository.add(cartItemVO);
    }

    @Override
    public OmsCartItem getCartItem(OmsCartItem cartItem) {
        return omsCartItemRepository.getCartItem(cartItem);
    }

    @Override
    public List<OmsCartItemVO> list(Long memberId) {
        return omsCartItemRepository.list(memberId);
    }

    @Override
    public List<CartPromotionItemResult> listPromotion(Long memberId, List<Long> cartIds) {
        return omsCartItemRepository.listPromotion(memberId,cartIds);
    }

    @Override
    public List<CartPromotionItem> listPromotionItem(Long memberId, List<Long> cartIds) {
        return omsCartItemRepository.listPromotionItem(memberId,cartIds);
    }

    @Override
    public int updateQuantity(Long id, Long memberId, Integer quantity) {
        return omsCartItemRepository.updateQuantity(id,memberId,quantity);
    }

    @Override
    public int delete(Long memberId, List<Long> ids) {
        return omsCartItemRepository.delete(memberId,ids);
    }

    @Override
    public CartProductResult getCartProduct(Long productId) {
        return omsCartItemRepository.getCartProduct(productId);
    }

    @Override
    public int updateAttr(OmsCartItemVO cartItemVO) {
        return omsCartItemRepository.updateAttr(cartItemVO);
    }

    @Override
    public int clear(Long memberId) {
        return omsCartItemRepository.clear(memberId);
    }
}

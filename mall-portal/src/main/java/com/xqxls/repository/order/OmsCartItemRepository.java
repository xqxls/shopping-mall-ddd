package com.xqxls.repository.order;

import cn.hutool.core.collection.CollUtil;
import com.xqxls.convert.order.OmsCartItemConvert;
import com.xqxls.convert.product.PmsPortalProductConvert;
import com.xqxls.dao.PortalProductDao;
import com.xqxls.domain.order.model.res.CartProductResult;
import com.xqxls.domain.order.model.res.CartPromotionItemResult;
import com.xqxls.domain.order.model.vo.OmsCartItemVO;
import com.xqxls.domain.order.repository.IOmsCartItemRepository;
import com.xqxls.domain.order.service.OmsPromotionService;
import com.xqxls.domain.product.model.vo.PmsProductAttributeVO;
import com.xqxls.domain.product.model.vo.PmsSkuStockVO;
import com.xqxls.dto.CartProduct;
import com.xqxls.dto.CartPromotionItem;
import com.xqxls.mapper.OmsCartItemMapper;
import com.xqxls.model.OmsCartItem;
import com.xqxls.model.UmsMember;
import com.xqxls.repository.member.UmsMemberRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/3 21:09
 */
@Repository
public class OmsCartItemRepository implements IOmsCartItemRepository {

    @Resource
    private OmsCartItemMapper cartItemMapper;
    @Resource
    private PortalProductDao productDao;
    @Resource
    private OmsPromotionRepository promotionRepository;
    @Resource
    private UmsMemberRepository umsMemberRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int add(OmsCartItemVO cartItemVO) {
        int count;
        UmsMember currentMember =umsMemberRepository.getCurrentMember();
        OmsCartItem cartItem = OmsCartItemConvert.INSTANCE.convertVOToEntity(cartItemVO);
        cartItem.setMemberId(currentMember.getId());
        cartItem.setMemberNickname(currentMember.getNickname());
        cartItem.setDeleteStatus(0);
        OmsCartItem existCartItem = this.getCartItem(cartItem);
        if (existCartItem == null) {
            cartItem.setCreateDate(new Date());
            count = cartItemMapper.insert(cartItem);
        } else {
            cartItem.setModifyDate(new Date());
            existCartItem.setQuantity(existCartItem.getQuantity() + cartItem.getQuantity());
            count = cartItemMapper.updateByPrimaryKey(existCartItem);
        }
        return count;
    }

    @Override
    public OmsCartItem getCartItem(OmsCartItem cartItem) {
        Example example = new Example(OmsCartItem.class);
        Example.Criteria criteria = example.createCriteria().andEqualTo("memberId",cartItem.getMemberId())
                .andEqualTo("productId",cartItem.getProductId()).andEqualTo("deleteStatus",0);
        if (cartItem.getProductSkuId()!=null) {
            criteria.andEqualTo("productSkuId",cartItem.getProductSkuId());
        }
        List<OmsCartItem> cartItemList = cartItemMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(cartItemList)) {
            return cartItemList.get(0);
        }
        return null;
    }

    @Override
    public List<OmsCartItemVO> list(Long memberId) {
        Example example = new Example(OmsCartItem.class);
        example.createCriteria().andEqualTo("deleteStatus",0).andEqualTo("memberId",memberId);
        return OmsCartItemConvert.INSTANCE.convertEntityToVOList(cartItemMapper.selectByExample(example));
    }

    @Override
    public List<CartPromotionItemResult> listPromotion(Long memberId, List<Long> cartIds) {
        List<OmsCartItemVO> cartItemVOList = list(memberId);
        if(CollUtil.isNotEmpty(cartIds)){
            cartItemVOList = cartItemVOList.stream().filter(item->cartIds.contains(item.getId())).collect(Collectors.toList());
        }
        List<CartPromotionItemResult> cartPromotionItemList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(cartItemVOList)){
            cartPromotionItemList = promotionRepository.calcCartPromotion(cartItemVOList);
        }
        return cartPromotionItemList;
    }

    @Override
    public List<CartPromotionItem> listPromotionItem(Long memberId, List<Long> cartIds) {
        List<OmsCartItemVO> cartItemVOList = list(memberId);
        if(CollUtil.isNotEmpty(cartIds)){
            cartItemVOList = cartItemVOList.stream().filter(item->cartIds.contains(item.getId())).collect(Collectors.toList());
        }
        List<CartPromotionItem> cartPromotionItemList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(cartItemVOList)){
            cartPromotionItemList = promotionRepository.calcCartPromotionItem(cartItemVOList);
        }
        return cartPromotionItemList;
    }

    @Override
    public int updateQuantity(Long id, Long memberId, Integer quantity) {
        OmsCartItem cartItem = new OmsCartItem();
        cartItem.setQuantity(quantity);
        Example example = new Example(OmsCartItem.class);
        example.createCriteria().andEqualTo("deleteStatus",0)
                .andEqualTo("id",id).andEqualTo("memberId",memberId);
        return cartItemMapper.updateByExampleSelective(cartItem, example);
    }

    @Override
    public int delete(Long memberId, List<Long> ids) {
        OmsCartItem record = new OmsCartItem();
        record.setDeleteStatus(1);
        Example example = new Example(OmsCartItem.class);
        example.createCriteria().andIn("id",ids).andEqualTo("memberId",memberId);
        return cartItemMapper.updateByExampleSelective(record, example);
    }

    @Override
    public CartProductResult getCartProduct(Long productId) {
        CartProduct cartProduct = productDao.getCartProduct(productId);
        CartProductResult cartProductResult = new CartProductResult();
        BeanUtils.copyProperties(cartProduct,cartProductResult);
        List<PmsProductAttributeVO> productAttributeVOList = PmsPortalProductConvert.INSTANCE.pmsProductAttributeEntityToVOList(cartProduct.getProductAttributeList());
        List<PmsSkuStockVO> skuStockVOList = PmsPortalProductConvert.INSTANCE.pmsSkuStockEntityToVOList(cartProduct.getSkuStockList());
        cartProductResult.setProductAttributeVOList(productAttributeVOList);
        cartProductResult.setSkuStockVOList(skuStockVOList);
        return cartProductResult;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateAttr(OmsCartItemVO cartItemVO) {
        //删除原购物车信息
        OmsCartItem updateCart = new OmsCartItem();
        updateCart.setId(cartItemVO.getId());
        updateCart.setModifyDate(new Date());
        updateCart.setDeleteStatus(1);
        cartItemMapper.updateByPrimaryKeySelective(updateCart);
        cartItemVO.setId(null);
        add(cartItemVO);
        return 1;
    }


    @Override
    public int clear(Long memberId) {
        OmsCartItem record = new OmsCartItem();
        record.setDeleteStatus(1);
        Example example = new Example(OmsCartItem.class);
        example.createCriteria().andEqualTo("memberId",memberId);
        return cartItemMapper.updateByExampleSelective(record,example);
    }
}

package com.xqxls.domain.order.service.calculatePromotion;

import com.xqxls.convert.order.OmsPromotionConvert;
import com.xqxls.domain.order.model.vo.OmsCartItemVO;
import com.xqxls.domain.product.service.PmsPortalProductService;
import com.xqxls.dto.CartPromotionItem;
import com.xqxls.dto.PromotionProduct;
import com.xqxls.model.OmsCartItem;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/14 21:37
 */
@Service
public class CalcCartPromotionItemService {

    @Resource
    private PmsPortalProductService pmsPortalProductService;

    /**
     * 计算购物车中的促销活动信息
     * @param cartItemVOList 购物车
     */
    public List<CartPromotionItem> calcCartPromotionItem(List<OmsCartItemVO> cartItemVOList){
        List<OmsCartItem> cartItemList = OmsPromotionConvert.INSTANCE.omsCartItemVOToEntityList(cartItemVOList);
        //1.先根据productId对CartItem进行分组，以spu为单位进行计算优惠
        Map<Long, List<OmsCartItem>> productCartMap = groupCartItemBySpu(cartItemList);
        //2.查询所有商品的优惠相关信息
        List<PromotionProduct> promotionProductList = getPromotionProductList(cartItemList);
        //3.根据商品促销类型计算商品促销优惠价格
        List<CartPromotionItem> cartPromotionItemList = new ArrayList<>();
        for (Map.Entry<Long, List<OmsCartItem>> entry : productCartMap.entrySet()) {
            Long productId = entry.getKey();
            PromotionProduct promotionProduct = getPromotionProductById(productId, promotionProductList);
            List<OmsCartItem> itemList = entry.getValue();
            Integer promotionType = promotionProduct.getPromotionType();
            ICalcCartPromotionItem calcCartPromotionItem = CalcStrategyContext.calcCartPromotionItemMap.get(promotionType);
            calcCartPromotionItem.handleCartItemList(cartPromotionItemList,itemList,promotionProduct,cartItemVOList);
        }
        return cartPromotionItemList;
    }

    /**
     * 以spu为单位对购物车中商品进行分组
     */
    private Map<Long, List<OmsCartItem>> groupCartItemBySpu(List<OmsCartItem> cartItemList) {
        Map<Long, List<OmsCartItem>> productCartMap = new TreeMap<>();
        for (OmsCartItem cartItem : cartItemList) {
            List<OmsCartItem> productCartItemList = productCartMap.get(cartItem.getProductId());
            if (productCartItemList == null) {
                productCartItemList = new ArrayList<>();
                productCartItemList.add(cartItem);
                productCartMap.put(cartItem.getProductId(), productCartItemList);
            } else {
                productCartItemList.add(cartItem);
            }
        }
        return productCartMap;
    }

    /**
     * 查询所有商品的优惠相关信息
     */
    protected List<PromotionProduct> getPromotionProductList(List<OmsCartItem> cartItemList) {
        List<Long> productIdList = new ArrayList<>();
        for(OmsCartItem cartItem:cartItemList){
            productIdList.add(cartItem.getProductId());
        }
        return pmsPortalProductService.getPromotionProductList(productIdList);
    }

    /**
     * 根据商品id获取商品的促销信息
     */
    protected PromotionProduct getPromotionProductById(Long productId, List<PromotionProduct> promotionProductList) {
        for (PromotionProduct promotionProduct : promotionProductList) {
            if (productId.equals(promotionProduct.getId())) {
                return promotionProduct;
            }
        }
        return null;
    }
}

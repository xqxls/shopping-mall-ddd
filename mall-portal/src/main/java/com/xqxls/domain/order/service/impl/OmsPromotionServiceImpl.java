package com.xqxls.domain.order.service.impl;

import com.xqxls.domain.order.model.res.CartPromotionItemResult;
import com.xqxls.domain.order.model.vo.OmsCartItemVO;
import com.xqxls.domain.order.repository.IOmsPromotionRepository;
import com.xqxls.domain.order.service.OmsPromotionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by macro on 2018/8/27.
 * 促销管理Service实现类
 */
@Service
public class OmsPromotionServiceImpl implements OmsPromotionService {
    @Resource
    private IOmsPromotionRepository omsPromotionRepository;

    @Override
    public List<CartPromotionItemResult> calcCartPromotion(List<OmsCartItemVO> cartItemVOList) {
        return omsPromotionRepository.calcCartPromotion(cartItemVOList);
    }
}

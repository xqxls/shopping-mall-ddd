package com.xqxls.domain.order.service.calculatePromotion;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/14 19:16
 */
@Service
public class CalcStrategyContext {

    @Resource
    private ICalcCartPromotionItem singleStrategy;
    @Resource
    private ICalcCartPromotionItem ladderStrategy;
    @Resource
    private ICalcCartPromotionItem fullReductionStrategy;
    @Resource
    private ICalcCartPromotionItem noReduceStrategy;

    protected static Map<Integer, ICalcCartPromotionItem> calcCartPromotionItemMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        calcCartPromotionItemMap.put(CalcStrategy.SINGLE.getCode(), singleStrategy);
        calcCartPromotionItemMap.put(CalcStrategy.FULL_REDUCTION.getCode(), fullReductionStrategy);
        calcCartPromotionItemMap.put(CalcStrategy.LADDER.getCode(), ladderStrategy);
        calcCartPromotionItemMap.put(CalcStrategy.NO_REDUCE.getCode(), noReduceStrategy);
    }
}

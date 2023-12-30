package com.xqxls.sms.service.impl;

import com.xqxls.sms.model.vo.SmsCouponHistoryVO;
import com.xqxls.sms.repository.ISmsCouponHistoryRepository;
import com.xqxls.sms.service.SmsCouponHistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 优惠券领取记录管理Service实现类
 * Created by xqxls on 2018/11/6.
 */
@Service
public class SmsCouponHistoryServiceImpl implements SmsCouponHistoryService {
    @Resource
    private ISmsCouponHistoryRepository smsCouponHistoryRepository;
    @Override
    public List<SmsCouponHistoryVO> list(Long couponId, Integer useStatus, String orderSn, Integer pageSize, Integer pageNum) {
        return smsCouponHistoryRepository.list(couponId,useStatus,orderSn,pageSize,pageNum);
    }
}

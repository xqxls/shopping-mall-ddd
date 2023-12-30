package com.xqxls.sms.service.impl;

import com.xqxls.sms.model.aggregates.SmsCouponRich;
import com.xqxls.sms.model.vo.SmsCouponVO;
import com.xqxls.sms.repository.ISmsCouponRepository;
import com.xqxls.sms.service.SmsCouponService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 优惠券管理Service实现类
 * Created by xqxls on 2018/8/28.
 */
@Service
public class SmsCouponServiceImpl implements SmsCouponService {
    @Resource
    private ISmsCouponRepository smsCouponRepository;

    @Override
    public int create(SmsCouponRich couponRich) {
        return smsCouponRepository.create(couponRich);
    }

    @Override
    public int delete(Long id) {
        return smsCouponRepository.delete(id);
    }

    @Override
    public int update(Long id, SmsCouponRich couponRich) {
        return smsCouponRepository.update(id,couponRich);
    }

    @Override
    public List<SmsCouponVO> list(String name, Integer type, Integer pageSize, Integer pageNum) {
        return smsCouponRepository.list(name,type,pageSize,pageNum);
    }

    @Override
    public SmsCouponRich getItem(Long id) {
        return smsCouponRepository.getItem(id);
    }
}

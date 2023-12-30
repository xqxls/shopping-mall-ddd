package com.xqxls.repository.sms;

import com.github.pagehelper.PageHelper;
import com.xqxls.convert.sms.SmsCouponHistoryConvert;
import com.xqxls.mapper.SmsCouponHistoryMapper;
import com.xqxls.model.SmsCouponHistoryExample;
import com.xqxls.sms.model.vo.SmsCouponHistoryVO;
import com.xqxls.sms.repository.ISmsCouponHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/29 22:49
 */
@Repository
public class SmsCouponHistoryRepository implements ISmsCouponHistoryRepository {

    @Resource
    private SmsCouponHistoryMapper historyMapper;

    @Override
    public List<SmsCouponHistoryVO> list(Long couponId, Integer useStatus, String orderSn, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        SmsCouponHistoryExample example = new SmsCouponHistoryExample();
        SmsCouponHistoryExample.Criteria criteria = example.createCriteria();
        if(couponId!=null){
            criteria.andCouponIdEqualTo(couponId);
        }
        if(useStatus!=null){
            criteria.andUseStatusEqualTo(useStatus);
        }
        if(StringUtils.hasText(orderSn)){
            criteria.andOrderSnEqualTo(orderSn);
        }
        return SmsCouponHistoryConvert.INSTANCE.smsCouponHistoryEntityToVOList(historyMapper.selectByExample(example));
    }
}

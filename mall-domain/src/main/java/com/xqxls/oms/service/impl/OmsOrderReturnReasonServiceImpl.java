package com.xqxls.oms.service.impl;

import com.xqxls.oms.model.vo.OmsOrderReturnReasonVO;
import com.xqxls.oms.repository.IOmsOrderReturnReasonRepository;
import com.xqxls.oms.service.OmsOrderReturnReasonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单原因管理Service实现类
 * Created by xqxls on 2018/10/17.
 */
@Service
public class OmsOrderReturnReasonServiceImpl implements OmsOrderReturnReasonService {
    @Resource
    private IOmsOrderReturnReasonRepository omsOrderReturnReasonRepository;

    @Override
    public int create(OmsOrderReturnReasonVO returnReasonVO) {
        return omsOrderReturnReasonRepository.create(returnReasonVO);
    }

    @Override
    public int update(Long id, OmsOrderReturnReasonVO returnReasonVO) {
        return omsOrderReturnReasonRepository.update(id,returnReasonVO);
    }

    @Override
    public int delete(List<Long> ids) {
        return omsOrderReturnReasonRepository.delete(ids);
    }

    @Override
    public List<OmsOrderReturnReasonVO> list(Integer pageSize, Integer pageNum) {
        return omsOrderReturnReasonRepository.list(pageSize,pageNum);
    }

    @Override
    public int updateStatus(List<Long> ids, Integer status) {
        return omsOrderReturnReasonRepository.updateStatus(ids,status);
    }

    @Override
    public OmsOrderReturnReasonVO getItem(Long id) {
        return omsOrderReturnReasonRepository.getItem(id);
    }
}

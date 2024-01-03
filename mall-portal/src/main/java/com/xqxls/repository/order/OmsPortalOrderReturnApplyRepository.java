package com.xqxls.repository.order;

import com.xqxls.domain.order.model.req.OmsOrderReturnApplyReq;
import com.xqxls.domain.order.repository.IOmsPortalOrderReturnApplyRepository;
import com.xqxls.mapper.OmsOrderReturnApplyMapper;
import com.xqxls.model.OmsOrderReturnApply;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/3 9:11
 */
@Repository
public class OmsPortalOrderReturnApplyRepository implements IOmsPortalOrderReturnApplyRepository {

    @Resource
    private OmsOrderReturnApplyMapper returnApplyMapper;

    @Override
    public int create(OmsOrderReturnApplyReq returnApplyReq) {
        OmsOrderReturnApply realApply = new OmsOrderReturnApply();
        BeanUtils.copyProperties(returnApplyReq,realApply);
        realApply.setCreateTime(new Date());
        realApply.setStatus(0);
        return returnApplyMapper.insert(realApply);
    }
}

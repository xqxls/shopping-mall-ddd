package com.xqxls.repository.oms;

import com.github.pagehelper.PageHelper;
import com.xqxls.convert.oms.OmsCompanyAddressConvert;
import com.xqxls.convert.oms.OmsOrderReturnApplyConvert;
import com.xqxls.dao.OmsOrderReturnApplyDao;
import com.xqxls.dto.OmsOrderReturnApplyResult;
import com.xqxls.dto.OmsReturnApplyQueryParam;
import com.xqxls.mapper.OmsOrderReturnApplyMapper;
import com.xqxls.model.OmsOrderReturnApply;
import com.xqxls.oms.model.aggregates.OmsOrderReturnApplyRich;
import com.xqxls.oms.model.req.OmsReturnApplyReq;
import com.xqxls.oms.model.req.OmsUpdateStatusReq;
import com.xqxls.oms.model.vo.OmsCompanyAddressVO;
import com.xqxls.oms.model.vo.OmsOrderReturnApplyVO;
import com.xqxls.oms.repository.IOmsOrderReturnApplyRepository;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/29 9:58
 */
@Repository
public class OmsOrderReturnApplyRepository implements IOmsOrderReturnApplyRepository {

    @Resource
    private OmsOrderReturnApplyDao returnApplyDao;
    @Resource
    private OmsOrderReturnApplyMapper returnApplyMapper;

    @Override
    public List<OmsOrderReturnApplyVO> list(OmsReturnApplyReq omsReturnApplyReq, Integer pageSize, Integer pageNum) {
        OmsReturnApplyQueryParam queryParam = OmsOrderReturnApplyConvert.INSTANCE.omsReturnApplyReqToQuery(omsReturnApplyReq);
        PageHelper.startPage(pageNum,pageSize);
        return OmsOrderReturnApplyConvert.INSTANCE.omsReturnApplyEntityToVOList(returnApplyDao.getList(queryParam));
    }

    @Override
    public int delete(List<Long> ids) {
        Example example = new Example(OmsOrderReturnApply.class);
        example.createCriteria().andIn("id",ids).andEqualTo("status",3);
        return returnApplyMapper.deleteByExample(example);
    }

    @Override
    public int updateStatus(Long id, OmsUpdateStatusReq omsUpdateStatusReq) {
        Integer status = omsUpdateStatusReq.getStatus();
        OmsOrderReturnApply returnApply = new OmsOrderReturnApply();
        if(status.equals(1)){
            //确认退货
            returnApply.setId(id);
            returnApply.setStatus(1);
            returnApply.setReturnAmount(omsUpdateStatusReq.getReturnAmount());
            returnApply.setCompanyAddressId(omsUpdateStatusReq.getCompanyAddressId());
            returnApply.setHandleTime(new Date());
            returnApply.setHandleMan(omsUpdateStatusReq.getHandleMan());
            returnApply.setHandleNote(omsUpdateStatusReq.getHandleNote());
        }else if(status.equals(2)){
            //完成退货
            returnApply.setId(id);
            returnApply.setStatus(2);
            returnApply.setReceiveTime(new Date());
            returnApply.setReceiveMan(omsUpdateStatusReq.getReceiveMan());
            returnApply.setReceiveNote(omsUpdateStatusReq.getReceiveNote());
        }else if(status.equals(3)){
            //拒绝退货
            returnApply.setId(id);
            returnApply.setStatus(3);
            returnApply.setHandleTime(new Date());
            returnApply.setHandleMan(omsUpdateStatusReq.getHandleMan());
            returnApply.setHandleNote(omsUpdateStatusReq.getHandleNote());
        }else{
            return 0;
        }
        return returnApplyMapper.updateByPrimaryKeySelective(returnApply);
    }

    @Override
    public OmsOrderReturnApplyRich getItem(Long id) {
        OmsOrderReturnApplyResult omsOrderReturnApplyResult = returnApplyDao.getDetail(id);
        OmsOrderReturnApplyVO omsOrderReturnApplyVO = OmsOrderReturnApplyConvert.INSTANCE.omsReturnApplyEntityToVO(omsOrderReturnApplyResult);
        OmsCompanyAddressVO companyAddressVO = OmsCompanyAddressConvert.INSTANCE.omsCompanyAddressEntityToVO(omsOrderReturnApplyResult.getCompanyAddress());

        OmsOrderReturnApplyRich omsOrderReturnApplyRich = new OmsOrderReturnApplyRich();
        omsOrderReturnApplyRich.setOmsOrderReturnApplyVO(omsOrderReturnApplyVO);
        omsOrderReturnApplyRich.setCompanyAddressVO(companyAddressVO);
        return omsOrderReturnApplyRich;
    }
}

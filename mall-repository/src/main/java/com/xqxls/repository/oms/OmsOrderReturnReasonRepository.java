package com.xqxls.repository.oms;

import com.github.pagehelper.PageHelper;
import com.xqxls.convert.oms.OmsOrderReturnReasonConvert;
import com.xqxls.mapper.OmsOrderReturnReasonMapper;
import com.xqxls.model.OmsOrderReturnReason;
import com.xqxls.oms.model.vo.OmsOrderReturnReasonVO;
import com.xqxls.oms.repository.IOmsOrderReturnReasonRepository;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/29 11:08
 */
@Repository
public class OmsOrderReturnReasonRepository implements IOmsOrderReturnReasonRepository {

    @Resource
    private OmsOrderReturnReasonMapper returnReasonMapper;

    @Override
    public int create(OmsOrderReturnReasonVO returnReasonVO) {
        OmsOrderReturnReason returnReason = OmsOrderReturnReasonConvert.INSTANCE.omsOrderReturnReasonVOToEntity(returnReasonVO);
        returnReason.setCreateTime(new Date());
        return returnReasonMapper.insert(returnReason);
    }

    @Override
    public int update(Long id, OmsOrderReturnReasonVO returnReasonVO) {
        OmsOrderReturnReason returnReason = OmsOrderReturnReasonConvert.INSTANCE.omsOrderReturnReasonVOToEntity(returnReasonVO);
        returnReason.setId(id);
        return returnReasonMapper.updateByPrimaryKey(returnReason);
    }

    @Override
    public int delete(List<Long> ids) {
        Example example = new Example(OmsOrderReturnReason.class);
        example.createCriteria().andIn("id",ids);
        return returnReasonMapper.deleteByExample(example);
    }

    @Override
    public List<OmsOrderReturnReasonVO> list(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        Example example = new Example(OmsOrderReturnReason.class);
        example.setOrderByClause("sort desc");
        return OmsOrderReturnReasonConvert.INSTANCE.omsOrderReturnReasonEntityToVOList(returnReasonMapper.selectByExample(example));
    }

    @Override
    public int updateStatus(List<Long> ids, Integer status) {
        if(!status.equals(0)&&!status.equals(1)){
            return 0;
        }
        OmsOrderReturnReason record = new OmsOrderReturnReason();
        record.setStatus(status);
        Example example = new Example(OmsOrderReturnReason.class);
        example.createCriteria().andIn("id",ids);
        return returnReasonMapper.updateByExampleSelective(record,example);
    }

    @Override
    public OmsOrderReturnReasonVO getItem(Long id) {
        return OmsOrderReturnReasonConvert.INSTANCE.omsOrderReturnReasonEntityToVO(returnReasonMapper.selectByPrimaryKey(id));
    }
}

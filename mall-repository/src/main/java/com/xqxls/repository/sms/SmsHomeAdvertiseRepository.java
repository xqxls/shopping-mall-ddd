package com.xqxls.repository.sms;

import com.github.pagehelper.PageHelper;
import com.xqxls.convert.sms.SmsHomeAdvertiseConvert;
import com.xqxls.mapper.SmsHomeAdvertiseMapper;
import com.xqxls.model.SmsHomeAdvertise;
import com.xqxls.model.SmsHomeAdvertiseExample;
import com.xqxls.sms.model.vo.SmsHomeAdvertiseVO;
import com.xqxls.sms.repository.ISmsHomeAdvertiseRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/30 17:31
 */
@Repository
public class SmsHomeAdvertiseRepository implements ISmsHomeAdvertiseRepository {

    @Resource
    private SmsHomeAdvertiseMapper smsHomeAdvertiseMapper;

    @Override
    public int create(SmsHomeAdvertiseVO advertiseVO) {
        SmsHomeAdvertise advertise = SmsHomeAdvertiseConvert.INSTANCE.smsHomeAdvertiseVOToEntity(advertiseVO);
        advertise.setClickCount(0);
        advertise.setOrderCount(0);
        return smsHomeAdvertiseMapper.insert(advertise);
    }

    @Override
    public int delete(List<Long> ids) {
        SmsHomeAdvertiseExample example = new SmsHomeAdvertiseExample();
        example.createCriteria().andIdIn(ids);
        return smsHomeAdvertiseMapper.deleteByExample(example);
    }

    @Override
    public int updateStatus(Long id, Integer status) {
        SmsHomeAdvertise record = new SmsHomeAdvertise();
        record.setId(id);
        record.setStatus(status);
        return smsHomeAdvertiseMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public SmsHomeAdvertiseVO getItem(Long id) {
        return SmsHomeAdvertiseConvert.INSTANCE.smsHomeAdvertiseEntityToVO(smsHomeAdvertiseMapper.selectByPrimaryKey(id));
    }

    @Override
    public int update(Long id, SmsHomeAdvertiseVO advertiseVO) {
        SmsHomeAdvertise advertise = SmsHomeAdvertiseConvert.INSTANCE.smsHomeAdvertiseVOToEntity(advertiseVO);
        advertise.setId(id);
        return smsHomeAdvertiseMapper.updateByPrimaryKeySelective(advertise);
    }

    @Override
    public List<SmsHomeAdvertiseVO> list(String name, Integer type, String endTime, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        SmsHomeAdvertiseExample example = new SmsHomeAdvertiseExample();
        SmsHomeAdvertiseExample.Criteria criteria = example.createCriteria();
        if (StringUtils.hasText(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        if (type != null) {
            criteria.andTypeEqualTo(type);
        }
        if (StringUtils.hasText(endTime)) {
            String startStr = endTime + " 00:00:00";
            String endStr = endTime + " 23:59:59";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date start = null;
            try {
                start = sdf.parse(startStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date end = null;
            try {
                end = sdf.parse(endStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (start != null && end != null) {
                criteria.andEndTimeBetween(start, end);
            }
        }
        example.setOrderByClause("sort desc");
        return SmsHomeAdvertiseConvert.INSTANCE.smsHomeAdvertiseEntityToVOList(smsHomeAdvertiseMapper.selectByExample(example));
    }
}

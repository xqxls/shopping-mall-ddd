package com.xqxls.repository.oms;

import com.xqxls.convert.oms.OmsOrderSettingConvert;
import com.xqxls.mapper.OmsOrderSettingMapper;
import com.xqxls.model.OmsOrderSetting;
import com.xqxls.oms.model.vo.OmsOrderSettingVO;
import com.xqxls.oms.repository.IOmsOrderSettingRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/29 9:30
 */
@Repository
public class OmsOrderSettingRepository implements IOmsOrderSettingRepository {

    @Resource
    private OmsOrderSettingMapper omsOrderSettingMapper;

    @Override
    public OmsOrderSettingVO getItem(Long id) {
        return OmsOrderSettingConvert.INSTANCE.omsOrderSettingEntityToVO(omsOrderSettingMapper.selectByPrimaryKey(id));
    }

    @Override
    public int update(Long id, OmsOrderSettingVO orderSettingVO) {
        OmsOrderSetting omsOrderSetting = OmsOrderSettingConvert.INSTANCE.omsOrderSettingVOToEntity(orderSettingVO);
        omsOrderSetting.setId(id);
        return omsOrderSettingMapper.updateByPrimaryKey(omsOrderSetting);
    }
}

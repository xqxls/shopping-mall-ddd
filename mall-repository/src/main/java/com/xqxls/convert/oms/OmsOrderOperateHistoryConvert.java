package com.xqxls.convert.oms;

import com.xqxls.model.OmsOrderOperateHistory;
import com.xqxls.oms.model.vo.OmsOrderOperateHistoryVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/29 11:44
 */
@Mapper
public interface OmsOrderOperateHistoryConvert {

    OmsOrderOperateHistoryConvert INSTANCE = Mappers.getMapper(OmsOrderOperateHistoryConvert.class);

    List<OmsOrderOperateHistoryVO> omsOrderOperateHistoryEntityToVOList(List<OmsOrderOperateHistory> list);
}

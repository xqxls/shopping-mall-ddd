package com.xqxls.oms.model.res;

import com.xqxls.oms.model.vo.OmsOrderItemVO;
import com.xqxls.oms.model.vo.OmsOrderOperateHistoryVO;
import com.xqxls.oms.model.vo.OmsOrderVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/29 11:27
 */
public class OmsOrderDetailResult extends OmsOrderVO {
    @Getter
    @Setter
    @ApiModelProperty("订单商品列表")
    private List<OmsOrderItemVO> orderItemList;
    @Getter
    @Setter
    @ApiModelProperty("订单操作记录列表")
    private List<OmsOrderOperateHistoryVO> historyList;
}


package com.xqxls.domain.order.model.res;

import com.xqxls.domain.order.model.vo.OmsOrderItemVO;
import com.xqxls.domain.order.model.vo.OmsOrderVO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/3 21:34
 */
@Getter
@Setter
public class OmsOrderDetailResult extends OmsOrderVO {

    private List<OmsOrderItemVO> orderItemVOList;

}

package com.xqxls.oms.model.aggregates;

import com.xqxls.oms.model.vo.OmsCompanyAddressVO;
import com.xqxls.oms.model.vo.OmsOrderReturnApplyVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/29 9:52
 */
@Data
public class OmsOrderReturnApplyRich {

    @ApiModelProperty(value = "订单申请退货")
    private OmsOrderReturnApplyVO omsOrderReturnApplyVO;

    @ApiModelProperty(value = "公司收货地址")
    private OmsCompanyAddressVO companyAddressVO;
}

package com.xqxls.sms.model.res;

import com.xqxls.sms.model.vo.SmsFlashPromotionSessionVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/30 17:13
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class SmsFlashPromotionSessionDetailResult extends SmsFlashPromotionSessionVO {

    @ApiModelProperty("商品数量")
    private Long productCount;
}

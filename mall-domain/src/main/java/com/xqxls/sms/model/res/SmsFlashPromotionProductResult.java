package com.xqxls.sms.model.res;

import com.xqxls.pms.model.vo.PmsProductVO;
import com.xqxls.sms.model.vo.SmsFlashPromotionProductRelationVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/30 16:30
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class SmsFlashPromotionProductResult extends SmsFlashPromotionProductRelationVO {

    @ApiModelProperty("关联商品")
    private PmsProductVO product;
}

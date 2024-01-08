package com.xqxls.domain.product.model.vo;

import com.xqxls.dto.FlashPromotionProduct;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/4 19:06
 */
@Setter
@Getter
public class HomeFlashPromotionVO {
    private Date startTime;
    private Date endTime;
    private Date nextStartTime;
    private Date nextEndTime;
    //属于该秒杀活动的商品
    private List<FlashPromotionProductVO> productVOList;
}


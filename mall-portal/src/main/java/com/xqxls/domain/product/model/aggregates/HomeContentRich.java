package com.xqxls.domain.product.model.aggregates;

import com.xqxls.domain.product.model.vo.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/4 19:05
 */
@Getter
@Setter
public class HomeContentRich {

    //轮播广告
    private List<SmsHomeAdvertiseVO> advertiseList;
    //推荐品牌
    private List<PmsBrandVO> brandList;
    //当前秒杀场次
    private HomeFlashPromotionVO homeFlashPromotion;
    //新品推荐
    private List<PmsProductVO> newProductList;
    //人气推荐
    private List<PmsProductVO> hotProductList;
    //推荐专题
    private List<CmsSubjectVO> subjectList;
}

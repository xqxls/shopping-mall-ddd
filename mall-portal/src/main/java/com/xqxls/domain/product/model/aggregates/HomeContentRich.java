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
    private List<SmsHomeAdvertiseVO> advertiseVOList;
    //推荐品牌
    private List<PmsBrandVO> brandVOList;
    //当前秒杀场次
    private HomeFlashPromotionVO homeFlashPromotionVO;
    //新品推荐
    private List<PmsProductVO> newProductVOList;
    //人气推荐
    private List<PmsProductVO> hotProductVOList;
    //推荐专题
    private List<CmsSubjectVO> subjectVOList;
}

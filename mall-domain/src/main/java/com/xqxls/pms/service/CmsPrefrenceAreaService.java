package com.xqxls.pms.service;

import com.xqxls.pms.model.vo.CmsPrefrenceAreaVO;

import java.util.List;

/**
 * 商品优选管理Service
 * Created by xqxls on 2018/6/1.
 */
public interface CmsPrefrenceAreaService {
    /**
     * 获取所有优选专区
     */
    List<CmsPrefrenceAreaVO> listAll();
}
package com.xqxls.pms.repository;

import com.xqxls.pms.model.vo.CmsPrefrenceAreaVO;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/30 20:07
 */
public interface ICmsPrefrenceAreaRepository {

    /**
     * 获取所有优选专区
     */
    List<CmsPrefrenceAreaVO> listAll();
}


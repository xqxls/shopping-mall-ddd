package com.xqxls.pms.service;

import com.xqxls.pms.model.vo.CmsSubjectVO;

import java.util.List;

/**
 * 商品专题管理Service
 * Created by xqxls on 2018/6/1.
 */
public interface CmsSubjectService {
    /**
     * 查询所有专题
     */
    List<CmsSubjectVO> listAll();

    /**
     * 分页查询专题
     */
    List<CmsSubjectVO> list(String keyword, Integer pageNum, Integer pageSize);
}

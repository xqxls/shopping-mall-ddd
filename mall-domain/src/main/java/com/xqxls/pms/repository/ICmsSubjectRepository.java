package com.xqxls.pms.repository;

import com.xqxls.pms.model.vo.CmsSubjectVO;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/30 19:57
 */
public interface ICmsSubjectRepository {

    /**
     * 查询所有专题
     */
    List<CmsSubjectVO> listAll();

    /**
     * 分页查询专题
     */
    List<CmsSubjectVO> list(String keyword, Integer pageNum, Integer pageSize);
}

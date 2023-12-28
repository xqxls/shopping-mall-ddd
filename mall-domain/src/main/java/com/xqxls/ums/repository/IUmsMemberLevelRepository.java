package com.xqxls.ums.repository;

import com.xqxls.ums.model.vo.UmsMemberLevelVO;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/27 14:39
 */
public interface IUmsMemberLevelRepository {

    /**
     * 获取所有会员登录
     * @param defaultStatus 是否为默认会员
     */
    List<UmsMemberLevelVO> list(Integer defaultStatus);
}

package com.xqxls.ums.service;

import com.xqxls.ums.model.vo.UmsMemberLevelVO;

import java.util.List;

/**
 * 会员等级管理Service
 * Created by xqxls on 2018/4/26.
 */
public interface UmsMemberLevelService {
    /**
     * 获取所有会员登录
     * @param defaultStatus 是否为默认会员
     */
    List<UmsMemberLevelVO> list(Integer defaultStatus);
}

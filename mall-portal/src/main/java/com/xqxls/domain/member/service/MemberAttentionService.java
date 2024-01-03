package com.xqxls.domain.member.service;

import com.xqxls.domain.member.model.vo.MemberBrandAttentionVO;
import org.springframework.data.domain.Page;

/**
 * 会员关注Service
 * Created by macro on 2018/8/2.
 */
public interface MemberAttentionService {
    /**
     * 添加关注
     */
    int add(MemberBrandAttentionVO memberBrandAttentionVO);

    /**
     * 取消关注
     */
    int delete(Long brandId);

    /**
     * 获取用户关注列表
     */
    Page<MemberBrandAttentionVO> list(Integer pageNum, Integer pageSize);

    /**
     * 获取用户关注详情
     */
    MemberBrandAttentionVO detail(Long brandId);

    /**
     * 清空关注列表
     */
    void clear();
}

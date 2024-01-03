package com.xqxls.domain.member.repository;

import com.xqxls.domain.member.model.vo.MemberBrandAttentionVO;
import org.springframework.data.domain.Page;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/2 14:43
 */
public interface IMemberAttentionRepository {

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

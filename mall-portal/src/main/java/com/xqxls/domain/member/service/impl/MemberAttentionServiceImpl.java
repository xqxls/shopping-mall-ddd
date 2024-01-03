package com.xqxls.domain.member.service.impl;

import com.xqxls.domain.member.model.vo.MemberBrandAttentionVO;
import com.xqxls.domain.member.repository.IMemberAttentionRepository;
import com.xqxls.domain.member.service.MemberAttentionService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 会员关注Service实现类
 * Created by macro on 2018/8/2.
 */
@Service
public class MemberAttentionServiceImpl implements MemberAttentionService {
    @Resource
    private IMemberAttentionRepository memberBrandAttentionRepository;

    @Override
    public int add(MemberBrandAttentionVO memberBrandAttentionVO) {
        return memberBrandAttentionRepository.add(memberBrandAttentionVO);
    }

    @Override
    public int delete(Long brandId) {
        return memberBrandAttentionRepository.delete(brandId);
    }

    @Override
    public Page<MemberBrandAttentionVO> list(Integer pageNum, Integer pageSize) {
        return memberBrandAttentionRepository.list(pageNum,pageSize);
    }

    @Override
    public MemberBrandAttentionVO detail(Long brandId) {
        return memberBrandAttentionRepository.detail(brandId);
    }

    @Override
    public void clear() {
        memberBrandAttentionRepository.clear();
    }
}

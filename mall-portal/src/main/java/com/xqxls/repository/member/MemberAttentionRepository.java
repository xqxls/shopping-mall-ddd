package com.xqxls.repository.member;

import com.xqxls.convert.member.MemberBrandAttentionConvert;
import com.xqxls.dto.MemberBrandAttention;
import com.xqxls.domain.member.model.vo.MemberBrandAttentionVO;
import com.xqxls.domain.member.repository.IMemberAttentionRepository;
import com.xqxls.model.UmsMember;
import com.xqxls.mongo.MemberBrandAttentionMongoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/2 15:07
 */
@Repository
public class MemberAttentionRepository implements IMemberAttentionRepository {

    @Resource
    private MemberBrandAttentionMongoRepository memberBrandAttentionRepository;
    @Resource
    private UmsMemberRepository umsMemberRepository;

    @Override
    public int add(MemberBrandAttentionVO memberBrandAttentionVO) {
        int count = 0;
        UmsMember member = umsMemberRepository.getCurrentMember();
        MemberBrandAttention memberBrandAttention = MemberBrandAttentionConvert.INSTANCE.convertVOToEntity(memberBrandAttentionVO);
        memberBrandAttention.setMemberId(member.getId());
        memberBrandAttention.setMemberNickname(member.getNickname());
        memberBrandAttention.setMemberIcon(member.getIcon());
        memberBrandAttention.setCreateTime(new Date());
        MemberBrandAttention findAttention = memberBrandAttentionRepository.findByMemberIdAndBrandId(memberBrandAttention.getMemberId(), memberBrandAttention.getBrandId());
        if (findAttention == null) {
            memberBrandAttentionRepository.save(memberBrandAttention);
            count = 1;
        }
        return count;
    }

    @Override
    public int delete(Long brandId) {
        UmsMember member = umsMemberRepository.getCurrentMember();
        return memberBrandAttentionRepository.deleteByMemberIdAndBrandId(member.getId(),brandId);
    }

    @Override
    public Page<MemberBrandAttentionVO> list(Integer pageNum, Integer pageSize) {
        UmsMember member = umsMemberRepository.getCurrentMember();
        Pageable pageable = PageRequest.of(pageNum-1,pageSize);
        Page<MemberBrandAttention> memberBrandAttentionPage = memberBrandAttentionRepository.findByMemberId(member.getId(),pageable);
        // 将Entity转换为VO
        List<MemberBrandAttentionVO> voList = memberBrandAttentionPage.getContent().stream()
                .map(MemberBrandAttentionConvert.INSTANCE::convertEntityToVO)
                .collect(Collectors.toList());
        return new PageImpl<>(voList, memberBrandAttentionPage.getPageable(), memberBrandAttentionPage.getTotalElements());
    }

    @Override
    public MemberBrandAttentionVO detail(Long brandId) {
        UmsMember member = umsMemberRepository.getCurrentMember();
        return MemberBrandAttentionConvert.INSTANCE.convertEntityToVO(memberBrandAttentionRepository.findByMemberIdAndBrandId(member.getId(), brandId));
    }

    @Override
    public void clear() {
        UmsMember member = umsMemberRepository.getCurrentMember();
        memberBrandAttentionRepository.deleteAllByMemberId(member.getId());
    }
}

package com.xqxls.repository.member;

import com.xqxls.convert.member.MemberReadHistoryConvert;
import com.xqxls.domain.member.model.vo.MemberReadHistoryVO;
import com.xqxls.domain.member.repository.IMemberReadHistoryRepository;
import com.xqxls.dto.MemberReadHistory;
import com.xqxls.model.UmsMember;
import com.xqxls.mongo.MemberReadHistoryMongoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/2 16:20
 */
@Repository
public class MemberReadHistoryRepository implements IMemberReadHistoryRepository {

    @Resource
    private MemberReadHistoryMongoRepository memberReadHistoryMongoRepository;

    @Resource
    private UmsMemberRepository umsMemberRepository;

    @Override
    public int create(MemberReadHistoryVO memberReadHistoryVO) {
        UmsMember member = umsMemberRepository.getCurrentMember();
        MemberReadHistory memberReadHistory = MemberReadHistoryConvert.INSTANCE.convertVOToEntity(memberReadHistoryVO);
        memberReadHistory.setMemberId(member.getId());
        memberReadHistory.setMemberNickname(member.getNickname());
        memberReadHistory.setMemberIcon(member.getIcon());
        memberReadHistory.setId(null);
        memberReadHistory.setCreateTime(new Date());
        memberReadHistoryMongoRepository.save(memberReadHistory);
        return 1;
    }

    @Override
    public int delete(List<String> ids) {
        List<MemberReadHistory> deleteList = new ArrayList<>();
        for(String id:ids){
            MemberReadHistory memberReadHistory = new MemberReadHistory();
            memberReadHistory.setId(id);
            deleteList.add(memberReadHistory);
        }
        memberReadHistoryMongoRepository.deleteAll(deleteList);
        return ids.size();
    }

    @Override
    public Page<MemberReadHistoryVO> list(Integer pageNum, Integer pageSize) {
        UmsMember member = umsMemberRepository.getCurrentMember();
        Pageable pageable = PageRequest.of(pageNum-1, pageSize);
        Page<MemberReadHistory> memberReadHistoryPage = memberReadHistoryMongoRepository.findByMemberIdOrderByCreateTimeDesc(member.getId(),pageable);
        // 将Entity转换为VO
        List<MemberReadHistoryVO> voList = memberReadHistoryPage.getContent().stream()
                .map(MemberReadHistoryConvert.INSTANCE::convertEntityToVO)
                .collect(Collectors.toList());
        return new PageImpl<>(voList, memberReadHistoryPage.getPageable(), memberReadHistoryPage.getTotalElements());
    }

    @Override
    public void clear() {
        UmsMember member = umsMemberRepository.getCurrentMember();
        memberReadHistoryMongoRepository.deleteAllByMemberId(member.getId());
    }
}

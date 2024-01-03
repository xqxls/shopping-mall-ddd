package com.xqxls.repository.member;

import com.xqxls.convert.member.MemberProductCollectionConvert;
import com.xqxls.domain.member.model.vo.MemberProductCollectionVO;
import com.xqxls.domain.member.repository.IMemberProductCollectionRepository;
import com.xqxls.dto.MemberProductCollection;
import com.xqxls.model.UmsMember;
import com.xqxls.mongo.MemberProductCollectionMongoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/2 16:05
 */
@Repository
public class MemberProductCollectionRepository implements IMemberProductCollectionRepository {

    @Resource
    private MemberProductCollectionMongoRepository productCollectionRepository;
    @Resource
    private UmsMemberRepository umsMemberRepository;

    @Override
    public int add(MemberProductCollectionVO productCollectionVO) {
        int count = 0;
        UmsMember member = umsMemberRepository.getCurrentMember();
        MemberProductCollection productCollection = MemberProductCollectionConvert.INSTANCE.convertVOToEntity(productCollectionVO);
        productCollection.setMemberId(member.getId());
        productCollection.setMemberNickname(member.getNickname());
        productCollection.setMemberIcon(member.getIcon());
        MemberProductCollection findCollection = productCollectionRepository.findByMemberIdAndProductId(productCollection.getMemberId(), productCollection.getProductId());
        if (findCollection == null) {
            productCollectionRepository.save(productCollection);
            count = 1;
        }
        return count;
    }

    @Override
    public int delete(Long productId) {
        UmsMember member = umsMemberRepository.getCurrentMember();
        return productCollectionRepository.deleteByMemberIdAndProductId(member.getId(), productId);
    }

    @Override
    public Page<MemberProductCollectionVO> list(Integer pageNum, Integer pageSize) {
        UmsMember member = umsMemberRepository.getCurrentMember();
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<MemberProductCollection> memberProductCollectionPage = productCollectionRepository.findByMemberId(member.getId(),pageable);
        // 将Entity转换为VO
        List<MemberProductCollectionVO> voList = memberProductCollectionPage.getContent().stream()
                .map(MemberProductCollectionConvert.INSTANCE::convertEntityToVO)
                .collect(Collectors.toList());
        return new PageImpl<>(voList, memberProductCollectionPage.getPageable(), memberProductCollectionPage.getTotalElements());
    }

    @Override
    public MemberProductCollectionVO detail(Long productId) {
        UmsMember member = umsMemberRepository.getCurrentMember();
        return MemberProductCollectionConvert.INSTANCE.convertEntityToVO(productCollectionRepository.findByMemberIdAndProductId(member.getId(), productId));
    }

    @Override
    public void clear() {
        UmsMember member = umsMemberRepository.getCurrentMember();
        productCollectionRepository.deleteAllByMemberId(member.getId());
    }
}

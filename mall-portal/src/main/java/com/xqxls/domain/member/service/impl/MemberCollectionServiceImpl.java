package com.xqxls.domain.member.service.impl;

import com.xqxls.domain.member.model.vo.MemberProductCollectionVO;
import com.xqxls.domain.member.repository.IMemberProductCollectionRepository;
import com.xqxls.domain.member.service.MemberCollectionService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 会员收藏Service实现类
 * Created by macro on 2018/8/2.
 */
@Service
public class MemberCollectionServiceImpl implements MemberCollectionService {
    @Resource
    private IMemberProductCollectionRepository memberCollectionRepository;

    @Override
    public int add(MemberProductCollectionVO productCollectionVO) {
        return memberCollectionRepository.add(productCollectionVO);
    }

    @Override
    public int delete(Long productId) {
        return memberCollectionRepository.delete(productId);
    }

    @Override
    public Page<MemberProductCollectionVO> list(Integer pageNum, Integer pageSize) {
        return memberCollectionRepository.list(pageNum,pageSize);
    }

    @Override
    public MemberProductCollectionVO detail(Long productId) {
        return memberCollectionRepository.detail(productId);
    }

    @Override
    public void clear() {
        memberCollectionRepository.clear();
    }
}

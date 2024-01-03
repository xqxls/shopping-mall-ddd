package com.xqxls.domain.member.service.impl;

import com.xqxls.domain.member.model.vo.MemberReadHistoryVO;
import com.xqxls.domain.member.repository.IMemberReadHistoryRepository;
import com.xqxls.domain.member.service.MemberReadHistoryService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 会员浏览记录管理Service实现类
 * Created by macro on 2018/8/3.
 */
@Service
public class MemberReadHistoryServiceImpl implements MemberReadHistoryService {
    @Resource
    private IMemberReadHistoryRepository memberReadHistoryRepository;

    @Override
    public int create(MemberReadHistoryVO memberReadHistoryVO) {
        return memberReadHistoryRepository.create(memberReadHistoryVO);
    }

    @Override
    public int delete(List<String> ids) {
        return memberReadHistoryRepository.delete(ids);
    }

    @Override
    public Page<MemberReadHistoryVO> list(Integer pageNum, Integer pageSize) {
        return memberReadHistoryRepository.list(pageNum,pageSize);
    }

    @Override
    public void clear() {
        memberReadHistoryRepository.clear();
    }
}

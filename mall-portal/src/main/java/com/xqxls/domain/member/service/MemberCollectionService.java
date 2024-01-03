package com.xqxls.domain.member.service;

import com.xqxls.domain.member.model.vo.MemberProductCollectionVO;
import org.springframework.data.domain.Page;

/**
 * 会员收藏Service
 * Created by macro on 2018/8/2.
 */
public interface MemberCollectionService {
    int add(MemberProductCollectionVO productCollectionVO);

    int delete(Long productId);

    Page<MemberProductCollectionVO> list(Integer pageNum, Integer pageSize);

    MemberProductCollectionVO detail(Long productId);

    void clear();
}

package com.xqxls.domain.member.repository;

import com.xqxls.domain.member.model.vo.MemberProductCollectionVO;
import org.springframework.data.domain.Page;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/2 16:04
 */
public interface IMemberProductCollectionRepository {

    int add(MemberProductCollectionVO productCollectionVO);

    int delete(Long productId);

    Page<MemberProductCollectionVO> list(Integer pageNum, Integer pageSize);

    MemberProductCollectionVO detail(Long productId);

    void clear();
}

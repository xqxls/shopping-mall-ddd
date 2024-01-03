package com.xqxls.domain.member.service;

import com.xqxls.domain.member.model.vo.MemberReadHistoryVO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 会员浏览记录管理Service
 * Created by macro on 2018/8/3.
 */
public interface MemberReadHistoryService {
    /**
     * 生成浏览记录
     */
    int create(MemberReadHistoryVO memberReadHistoryVO);

    /**
     * 批量删除浏览记录
     */
    int delete(List<String> ids);

    /**
     * 分页获取用户浏览历史记录
     */
    Page<MemberReadHistoryVO> list(Integer pageNum, Integer pageSize);

    /**
     * 清空浏览记录
     */
    void clear();
}

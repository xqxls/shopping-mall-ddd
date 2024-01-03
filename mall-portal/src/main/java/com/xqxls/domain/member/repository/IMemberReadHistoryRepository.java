package com.xqxls.domain.member.repository;

import com.xqxls.domain.member.model.vo.MemberReadHistoryVO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/2 16:20
 */
public interface IMemberReadHistoryRepository {

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

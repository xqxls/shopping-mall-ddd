package com.xqxls.sms.repository;

import com.xqxls.sms.model.vo.SmsHomeNewProductVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/30 17:46
 */
public interface ISmsHomeNewProductRepository {
    /**
     * 添加首页推荐
     */
    int create(List<SmsHomeNewProductVO> homeNewProductList);

    /**
     * 修改推荐排序
     */
    int updateSort(Long id, Integer sort);

    /**
     * 批量删除推荐
     */
    int delete(List<Long> ids);

    /**
     * 更新推荐状态
     */
    int updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    /**
     * 分页查询推荐
     */
    List<SmsHomeNewProductVO> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum);
}


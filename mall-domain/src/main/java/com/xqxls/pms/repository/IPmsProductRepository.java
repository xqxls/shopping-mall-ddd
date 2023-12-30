package com.xqxls.pms.repository;

import com.xqxls.pms.model.aggregates.PmsProductRich;
import com.xqxls.pms.model.req.PmsProductReq;
import com.xqxls.pms.model.res.PmsProductUpdateResult;
import com.xqxls.pms.model.vo.PmsProductVO;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/28 9:33
 */
public interface IPmsProductRepository {

    /**
     * 创建商品
     */
    int create(PmsProductRich pmsProductRich);

    /**
     * 根据商品编号获取更新信息
     */
    PmsProductUpdateResult getUpdateInfo(Long id);

    /**
     * 更新商品
     */
    int update(Long id, PmsProductRich pmsProductRich);

    /**
     * 分页查询商品
     */
    List<PmsProductVO> list(PmsProductReq pmsProductReq, Integer pageSize, Integer pageNum);

    /**
     * 批量修改审核状态
     * @param ids 产品id
     * @param verifyStatus 审核状态
     * @param detail 审核详情
     */
    int updateVerifyStatus(List<Long> ids, Integer verifyStatus, String detail);

    /**
     * 批量修改商品上架状态
     */
    int updatePublishStatus(List<Long> ids, Integer publishStatus);

    /**
     * 批量修改商品推荐状态
     */
    int updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    /**
     * 批量修改新品状态
     */
    int updateNewStatus(List<Long> ids, Integer newStatus);

    /**
     * 批量删除商品
     */
    int updateDeleteStatus(List<Long> ids, Integer deleteStatus);

    /**
     * 根据商品名称或者货号模糊查询
     */
    List<PmsProductVO> list(String keyword);
}

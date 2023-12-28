package com.xqxls.pms.repository;

import com.xqxls.pms.model.req.PmsProductCategoryReq;
import com.xqxls.pms.model.res.PmsProductCategoryWithChildrenItemResult;
import com.xqxls.pms.model.vo.PmsProductCategoryVO;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/28 9:33
 */
public interface IPmsProductCategoryRepository {

    /**
     * 创建商品分类
     */
    int create(PmsProductCategoryReq pmsProductCategoryReq);

    /**
     * 修改商品分类
     */
    int update(Long id, PmsProductCategoryReq pmsProductCategoryReq);

    /**
     * 分页获取商品分类
     */
    List<PmsProductCategoryVO> getList(Long parentId, Integer pageSize, Integer pageNum);

    /**
     * 删除商品分类
     */
    int delete(Long id);

    /**
     * 根据ID获取商品分类
     */
    PmsProductCategoryVO getItem(Long id);

    /**
     * 批量修改导航状态
     */
    int updateNavStatus(List<Long> ids, Integer navStatus);

    /**
     * 批量修改显示状态
     */
    int updateShowStatus(List<Long> ids, Integer showStatus);

    /**
     * 以层级形式获取商品分类
     */
    List<PmsProductCategoryWithChildrenItemResult> listWithChildren();
}

package com.xqxls.pms.service;

import com.xqxls.dto.PmsProductCategoryParam;
import com.xqxls.dto.PmsProductCategoryWithChildrenItem;
import com.xqxls.model.PmsProductCategory;
import com.xqxls.pms.model.req.PmsProductCategoryReq;
import com.xqxls.pms.model.res.PmsProductCategoryWithChildrenItemResult;
import com.xqxls.pms.model.vo.PmsProductCategoryVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品分类Service
 * Created by xqxls on 2018/4/26.
 */
public interface PmsProductCategoryService {
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

package com.xqxls.domain.product.repository;

import com.xqxls.domain.product.model.aggregates.HomeContentRich;
import com.xqxls.domain.product.model.vo.CmsSubjectVO;
import com.xqxls.domain.product.model.vo.PmsProductCategoryVO;
import com.xqxls.domain.product.model.vo.PmsProductVO;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/4 19:02
 */
public interface IHomeRepository {

    /**
     * 获取首页内容
     */
    HomeContentRich content();

    /**
     * 首页商品推荐
     */
    List<PmsProductVO> recommendProductList(Integer pageSize, Integer pageNum);

    /**
     * 获取商品分类
     * @param parentId 0:获取一级分类；其他：获取指定二级分类
     */
    List<PmsProductCategoryVO> getProductCateList(Long parentId);

    /**
     * 根据专题分类分页获取专题
     * @param cateId 专题分类id
     */
    List<CmsSubjectVO> getSubjectList(Long cateId, Integer pageSize, Integer pageNum);

    /**
     * 分页获取人气推荐商品
     */
    List<PmsProductVO> hotProductList(Integer pageNum, Integer pageSize);

    /**
     * 分页获取新品推荐商品
     */
    List<PmsProductVO> newProductList(Integer pageNum, Integer pageSize);
}

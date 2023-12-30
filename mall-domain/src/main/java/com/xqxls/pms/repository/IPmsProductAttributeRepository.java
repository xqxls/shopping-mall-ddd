package com.xqxls.pms.repository;

import com.xqxls.pms.model.req.PmsProductAttributeReq;
import com.xqxls.pms.model.res.ProductAttrInfoResult;
import com.xqxls.pms.model.vo.PmsProductAttributeVO;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/28 9:33
 */
public interface IPmsProductAttributeRepository {

    /**
     * 根据分类分页获取商品属性
     * @param cid 分类id
     * @param type 0->属性；2->参数
     */
    List<PmsProductAttributeVO> getList(Long cid, Integer type, Integer pageSize, Integer pageNum);

    /**
     * 添加商品属性
     */
    int create(PmsProductAttributeReq pmsProductAttributeReq);

    /**
     * 修改商品属性
     */
    int update(Long id, PmsProductAttributeReq pmsProductAttributeReq);

    /**
     * 获取单个商品属性信息
     */
    PmsProductAttributeVO getItem(Long id);

    int delete(List<Long> ids);

    List<ProductAttrInfoResult> getProductAttrInfo(Long productCategoryId);
}

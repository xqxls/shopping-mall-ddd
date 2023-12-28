package com.xqxls.pms.repository;

import com.xqxls.pms.model.res.PmsProductAttributeCategoryItemResult;
import com.xqxls.pms.model.vo.PmsProductAttributeCategoryVO;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/28 9:33
 */
public interface IPmsProductAttributeCategoryRepository {

    /**
     * 创建属性分类
     */
    int create(String name);

    /**
     * 修改属性分类
     */
    int update(Long id, String name);

    /**
     * 删除属性分类
     */
    int delete(Long id);

    /**
     * 获取属性分类详情
     */
    PmsProductAttributeCategoryVO getItem(Long id);

    /**
     * 分页查询属性分类
     */
    List<PmsProductAttributeCategoryVO> getList(Integer pageSize, Integer pageNum);

    /**
     * 获取包含属性的属性分类
     */
    List<PmsProductAttributeCategoryItemResult> getListWithAttr();
}

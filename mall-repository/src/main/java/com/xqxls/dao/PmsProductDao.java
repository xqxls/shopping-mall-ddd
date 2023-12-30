package com.xqxls.dao;

import com.xqxls.dto.PmsProductResult;
import com.xqxls.model.CmsPrefrenceAreaProductRelation;
import com.xqxls.model.CmsSubjectProductRelation;
import org.apache.ibatis.annotations.Param;


/**
 * 自定义商品管理Dao
 * Created by xqxls on 2018/4/26.
 */
public interface PmsProductDao {
    /**
     * 获取商品编辑信息
     */
    PmsProductResult getUpdateInfo(@Param("id") Long id);

    CmsSubjectProductRelation selectSubjectProductRelationByProductId(@Param("productId") Long productId);

    CmsPrefrenceAreaProductRelation selectPrefrenceAreaProductRelationByProductId(@Param("productId") Long productId);
}

package com.xqxls.pms.service;

import com.xqxls.pms.model.req.PmsBrandReq;
import com.xqxls.pms.model.vo.PmsBrandVO;

import java.util.List;

/**
 * 商品品牌Service
 * Created by xqxls on 2018/4/26.
 */
public interface PmsBrandService {
    /**
     * 获取所有品牌
     */
    List<PmsBrandVO> listAllBrand();

    /**
     * 创建品牌
     */
    int createBrand(PmsBrandReq pmsBrandReq);

    /**
     * 修改品牌
     */
    int updateBrand(Long id, PmsBrandReq pmsBrandReq);

    /**
     * 删除品牌
     */
    int deleteBrand(Long id);

    /**
     * 批量删除品牌
     */
    int deleteBrand(List<Long> ids);

    /**
     * 分页查询品牌
     */
    List<PmsBrandVO> listBrand(String keyword, int pageNum, int pageSize);

    /**
     * 获取品牌
     */
    PmsBrandVO getBrand(Long id);

    /**
     * 修改显示状态
     */
    int updateShowStatus(List<Long> ids, Integer showStatus);

    /**
     * 修改厂家制造商状态
     */
    int updateFactoryStatus(List<Long> ids, Integer factoryStatus);
}

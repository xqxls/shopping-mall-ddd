package com.xqxls.pms.service.impl;

import com.xqxls.pms.model.req.PmsProductAttributeReq;
import com.xqxls.pms.model.res.ProductAttrInfoResult;
import com.xqxls.pms.model.vo.PmsProductAttributeVO;
import com.xqxls.pms.repository.IPmsProductAttributeRepository;
import com.xqxls.pms.service.PmsProductAttributeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品属性Service实现类
 * Created by xqxls on 2018/4/26.
 */
@Service
public class PmsProductAttributeServiceImpl implements PmsProductAttributeService {
    @Resource
    private IPmsProductAttributeRepository pmsProductAttributeRepository;

    @Override
    public List<PmsProductAttributeVO> getList(Long cid, Integer type, Integer pageSize, Integer pageNum) {
        return pmsProductAttributeRepository.getList(cid,type,pageSize,pageNum);
    }

    @Override
    public int create(PmsProductAttributeReq pmsProductAttributeReq) {
        return pmsProductAttributeRepository.create(pmsProductAttributeReq);
    }

    @Override
    public int update(Long id, PmsProductAttributeReq pmsProductAttributeReq) {
        return pmsProductAttributeRepository.update(id,pmsProductAttributeReq);
    }

    @Override
    public PmsProductAttributeVO getItem(Long id) {
        return pmsProductAttributeRepository.getItem(id);
    }

    @Override
    public int delete(List<Long> ids) {
        return pmsProductAttributeRepository.delete(ids);
    }

    @Override
    public List<ProductAttrInfoResult> getProductAttrInfo(Long productCategoryId) {
        return pmsProductAttributeRepository.getProductAttrInfo(productCategoryId);
    }
}

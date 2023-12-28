package com.xqxls.pms.service.impl;

import com.xqxls.pms.model.aggregates.PmsProductRich;
import com.xqxls.pms.model.req.PmsProductReq;
import com.xqxls.pms.model.res.PmsProductUpdateResult;
import com.xqxls.pms.model.vo.PmsProductVO;
import com.xqxls.pms.service.PmsProductService;
import com.xqxls.repository.pms.PmsProductRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品管理Service实现类
 * Created by xqxls on 2018/4/26.
 */
@Service
public class PmsProductServiceImpl implements PmsProductService {
    @Resource
    private PmsProductRepository pmsProductRepository;

    @Override
    public int create(PmsProductRich pmsProductRich) {
        return pmsProductRepository.create(pmsProductRich);
    }

    @Override
    public PmsProductUpdateResult getUpdateInfo(Long id) {
        return pmsProductRepository.getUpdateInfo(id);
    }

    @Override
    public int update(Long id, PmsProductRich pmsProductRich) {
        return pmsProductRepository.update(id,pmsProductRich);
    }

    @Override
    public List<PmsProductVO> list(PmsProductReq pmsProductReq, Integer pageSize, Integer pageNum) {
        return pmsProductRepository.list(pmsProductReq,pageSize,pageNum);
    }

    @Override
    public int updateVerifyStatus(List<Long> ids, Integer verifyStatus, String detail) {
        return pmsProductRepository.updateVerifyStatus(ids,verifyStatus,detail);
    }

    @Override
    public int updatePublishStatus(List<Long> ids, Integer publishStatus) {
        return pmsProductRepository.updatePublishStatus(ids,publishStatus);
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        return pmsProductRepository.updateRecommendStatus(ids,recommendStatus);
    }

    @Override
    public int updateNewStatus(List<Long> ids, Integer newStatus) {
        return pmsProductRepository.updateNewStatus(ids,newStatus);
    }

    @Override
    public int updateDeleteStatus(List<Long> ids, Integer deleteStatus) {
        return pmsProductRepository.updateDeleteStatus(ids,deleteStatus);
    }

    @Override
    public List<PmsProductVO> list(String keyword) {
        return pmsProductRepository.list(keyword);
    }
}

package com.xqxls.pms.service.impl;

import com.xqxls.pms.model.req.PmsProductCategoryReq;
import com.xqxls.pms.model.res.PmsProductCategoryWithChildrenItemResult;
import com.xqxls.pms.model.vo.PmsProductCategoryVO;
import com.xqxls.pms.repository.IPmsProductCategoryRepository;
import com.xqxls.pms.service.PmsProductCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * PmsProductCategoryService实现类
 * Created by xqxls on 2018/4/26.
 */
@Service
public class PmsProductCategoryServiceImpl implements PmsProductCategoryService {
    @Resource
    private IPmsProductCategoryRepository pmsProductCategoryRepository;

    @Override
    public int create(PmsProductCategoryReq pmsProductCategoryReq) {
        return pmsProductCategoryRepository.create(pmsProductCategoryReq);
    }

    @Override
    public int update(Long id, PmsProductCategoryReq pmsProductCategoryReq) {
        return pmsProductCategoryRepository.update(id,pmsProductCategoryReq);
    }

    @Override
    public List<PmsProductCategoryVO> getList(Long parentId, Integer pageSize, Integer pageNum) {
        return pmsProductCategoryRepository.getList(parentId,pageSize,pageNum);
    }

    @Override
    public int delete(Long id) {
        return pmsProductCategoryRepository.delete(id);
    }

    @Override
    public PmsProductCategoryVO getItem(Long id) {
        return pmsProductCategoryRepository.getItem(id);
    }

    @Override
    public int updateNavStatus(List<Long> ids, Integer navStatus) {
        return pmsProductCategoryRepository.updateNavStatus(ids,navStatus);
    }

    @Override
    public int updateShowStatus(List<Long> ids, Integer showStatus) {
        return pmsProductCategoryRepository.updateShowStatus(ids,showStatus);
    }

    @Override
    public List<PmsProductCategoryWithChildrenItemResult> listWithChildren() {
        return pmsProductCategoryRepository.listWithChildren();
    }
}

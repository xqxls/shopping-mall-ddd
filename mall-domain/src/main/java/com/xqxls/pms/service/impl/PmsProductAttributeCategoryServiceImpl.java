package com.xqxls.pms.service.impl;

import com.xqxls.pms.model.res.PmsProductAttributeCategoryItemResult;
import com.xqxls.pms.model.vo.PmsProductAttributeCategoryVO;
import com.xqxls.pms.repository.IPmsProductAttributeCategoryRepository;
import com.xqxls.pms.service.PmsProductAttributeCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * PmsProductAttributeCategoryService实现类
 * Created by xqxls on 2018/4/26.
 */
@Service
public class PmsProductAttributeCategoryServiceImpl implements PmsProductAttributeCategoryService {
    @Resource
    private IPmsProductAttributeCategoryRepository pmsProductAttributeCategoryRepository;

    @Override
    public int create(String name) {
        return pmsProductAttributeCategoryRepository.create(name);
    }

    @Override
    public int update(Long id, String name) {
        return pmsProductAttributeCategoryRepository.update(id,name);
    }

    @Override
    public int delete(Long id) {
        return pmsProductAttributeCategoryRepository.delete(id);
    }

    @Override
    public PmsProductAttributeCategoryVO getItem(Long id) {
        return pmsProductAttributeCategoryRepository.getItem(id);
    }

    @Override
    public List<PmsProductAttributeCategoryVO> getList(Integer pageSize, Integer pageNum) {
        return pmsProductAttributeCategoryRepository.getList(pageSize,pageNum);
    }

    @Override
    public List<PmsProductAttributeCategoryItemResult> getListWithAttr() {
        return pmsProductAttributeCategoryRepository.getListWithAttr();
    }
}

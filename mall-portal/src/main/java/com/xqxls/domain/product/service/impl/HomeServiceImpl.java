package com.xqxls.domain.product.service.impl;

import com.xqxls.domain.product.model.aggregates.HomeContentRich;
import com.xqxls.domain.product.model.vo.CmsSubjectVO;
import com.xqxls.domain.product.model.vo.PmsProductCategoryVO;
import com.xqxls.domain.product.model.vo.PmsProductVO;
import com.xqxls.domain.product.repository.IHomeRepository;
import com.xqxls.domain.product.service.HomeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 首页内容管理Service实现类
 * Created by macro on 2019/1/28.
 */
@Service
public class HomeServiceImpl implements HomeService {
    @Resource
    private IHomeRepository homeRepository;

    @Override
    public HomeContentRich content() {
        return homeRepository.content();
    }

    @Override
    public List<PmsProductVO> recommendProductList(Integer pageSize, Integer pageNum) {
        return homeRepository.recommendProductList(pageSize,pageNum);
    }

    @Override
    public List<PmsProductCategoryVO> getProductCateList(Long parentId) {
        return homeRepository.getProductCateList(parentId);
    }

    @Override
    public List<CmsSubjectVO> getSubjectList(Long cateId, Integer pageSize, Integer pageNum) {
        return homeRepository.getSubjectList(cateId,pageSize,pageNum);
    }

    @Override
    public List<PmsProductVO> hotProductList(Integer pageNum, Integer pageSize) {
        return homeRepository.hotProductList(pageNum,pageSize);
    }

    @Override
    public List<PmsProductVO> newProductList(Integer pageNum, Integer pageSize) {
        return homeRepository.newProductList(pageNum,pageSize);
    }
}

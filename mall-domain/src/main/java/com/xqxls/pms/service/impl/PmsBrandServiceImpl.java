package com.xqxls.pms.service.impl;

import com.xqxls.pms.model.req.PmsBrandReq;
import com.xqxls.pms.model.vo.PmsBrandVO;
import com.xqxls.pms.repository.IPmsBrandRepository;
import com.xqxls.pms.service.PmsBrandService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品品牌Service实现类
 * Created by xqxls on 2018/4/26.
 */
@Service
public class PmsBrandServiceImpl implements PmsBrandService {
    @Resource
    private IPmsBrandRepository pmsBrandRepository;

    @Override
    public List<PmsBrandVO> listAllBrand() {
        return pmsBrandRepository.listAllBrand();
    }

    @Override
    public int createBrand(PmsBrandReq pmsBrandReq) {
        return pmsBrandRepository.createBrand(pmsBrandReq);
    }

    @Override
    public int updateBrand(Long id, PmsBrandReq pmsBrandReq) {
        return pmsBrandRepository.updateBrand(id,pmsBrandReq);
    }

    @Override
    public int deleteBrand(Long id) {
        return pmsBrandRepository.deleteBrand(id);
    }

    @Override
    public int deleteBrand(List<Long> ids) {
        return pmsBrandRepository.deleteBrand(ids);
    }

    @Override
    public List<PmsBrandVO> listBrand(String keyword, int pageNum, int pageSize) {
        return pmsBrandRepository.listBrand(keyword,pageNum,pageSize);
    }

    @Override
    public PmsBrandVO getBrand(Long id) {
        return pmsBrandRepository.getBrand(id);
    }

    @Override
    public int updateShowStatus(List<Long> ids, Integer showStatus) {
        return pmsBrandRepository.updateShowStatus(ids,showStatus);
    }

    @Override
    public int updateFactoryStatus(List<Long> ids, Integer factoryStatus) {
        return pmsBrandRepository.updateFactoryStatus(ids,factoryStatus);
    }
}

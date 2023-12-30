package com.xqxls.pms.service.impl;

import com.xqxls.pms.model.vo.CmsPrefrenceAreaVO;
import com.xqxls.pms.repository.ICmsPrefrenceAreaRepository;
import com.xqxls.pms.service.CmsPrefrenceAreaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品优选管理Service实现类
 * Created by xqxls on 2018/6/1.
 */
@Service
public class CmsPrefrenceAreaServiceImpl implements CmsPrefrenceAreaService {
    @Resource
    private ICmsPrefrenceAreaRepository cmsPrefrenceAreaRepository;

    @Override
    public List<CmsPrefrenceAreaVO> listAll() {
        return cmsPrefrenceAreaRepository.listAll();
    }
}

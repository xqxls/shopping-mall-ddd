package com.xqxls.domain.product.service.impl;

import com.xqxls.convert.member.UmsMemberConvert;
import com.xqxls.convert.product.SmsHomeAdvertiseConvert;
import com.xqxls.domain.product.model.aggregates.HomeContentRich;
import com.xqxls.domain.product.model.vo.CmsSubjectVO;
import com.xqxls.domain.product.model.vo.PmsProductCategoryVO;
import com.xqxls.domain.product.model.vo.PmsProductVO;
import com.xqxls.domain.product.repository.IHomeRepository;
import com.xqxls.domain.product.service.HomeService;
import com.xqxls.model.SmsHomeAdvertise;
import com.xqxls.model.UmsMember;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    public static void main(String[] args) {
        List<SmsHomeAdvertise> smsHomeAdvertiseList = new ArrayList<>();
        for(int i=0;i<3;i++){
            SmsHomeAdvertise smsHomeAdvertise = new SmsHomeAdvertise();
            smsHomeAdvertise.setId(i+1L);
            smsHomeAdvertise.setName(i+"1");
            smsHomeAdvertiseList.add(smsHomeAdvertise);
        }
        List<UmsMember> umsMemberList = new ArrayList<>();
        for(int i=0;i<3;i++){
            UmsMember umsMember = new UmsMember();
            umsMember.setId(i+1L);
            umsMember.setGender(i+1);
            umsMemberList.add(umsMember);
        }
        System.out.println(SmsHomeAdvertiseConvert.INSTANCE.convertEntityToVOList(smsHomeAdvertiseList));
        System.out.println(SmsHomeAdvertiseConvert.INSTANCE.convertEntityToVO(smsHomeAdvertiseList.get(0)));
        System.out.println(UmsMemberConvert.INSTANCE.convertEntityToVOList(umsMemberList));
        System.out.println(UmsMemberConvert.INSTANCE.convertEntityToVO(umsMemberList.get(0)));
    }
}

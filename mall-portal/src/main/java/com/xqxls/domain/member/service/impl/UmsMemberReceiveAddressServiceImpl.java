package com.xqxls.domain.member.service.impl;

import com.xqxls.domain.member.model.vo.UmsMemberReceiveAddressVO;
import com.xqxls.domain.member.repository.IUmsMemberReceiveAddressRepository;
import com.xqxls.domain.member.service.UmsMemberReceiveAddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户地址管理Service实现类
 * Created by macro on 2018/8/28.
 */
@Service
public class UmsMemberReceiveAddressServiceImpl implements UmsMemberReceiveAddressService {
    @Resource
    private IUmsMemberReceiveAddressRepository umsMemberReceiveAddressRepository;

    @Override
    public int add(UmsMemberReceiveAddressVO umsMemberReceiveAddressVO) {
        return umsMemberReceiveAddressRepository.add(umsMemberReceiveAddressVO);
    }

    @Override
    public int delete(Long id) {
        return umsMemberReceiveAddressRepository.delete(id);
    }

    @Override
    public int update(Long id, UmsMemberReceiveAddressVO umsMemberReceiveAddressVO) {
        return umsMemberReceiveAddressRepository.update(id,umsMemberReceiveAddressVO);
    }

    @Override
    public List<UmsMemberReceiveAddressVO> list() {
        return umsMemberReceiveAddressRepository.list();
    }

    @Override
    public UmsMemberReceiveAddressVO getItem(Long id) {
        return umsMemberReceiveAddressRepository.getItem(id);
    }
}

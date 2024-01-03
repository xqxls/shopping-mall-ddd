package com.xqxls.domain.member.service.impl;

import com.xqxls.domain.UserDto;
import com.xqxls.domain.member.model.vo.UmsMemberVO;
import com.xqxls.domain.member.repository.IUmsMemberRepository;
import com.xqxls.domain.member.service.UmsMemberService;
import com.xqxls.model.UmsMember;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 会员管理Service实现类
 * Created by macro on 2018/8/3.
 */
@Service
public class UmsMemberServiceImpl implements UmsMemberService {
    @Resource
    private IUmsMemberRepository umsMemberRepository;

    @Override
    public UmsMemberVO getByUsername(String username) {
        return umsMemberRepository.getByUsername(username);
    }

    @Override
    public UmsMemberVO getById(Long id) {
        return umsMemberRepository.getById(id);
    }

    @Override
    public void register(String username, String password, String telephone, String authCode) {
        umsMemberRepository.register(username,password,telephone,authCode);
    }

    @Override
    public String generateAuthCode(String telephone) {
        return umsMemberRepository.generateAuthCode(telephone);
    }

    @Override
    public void updatePassword(String telephone, String password, String authCode) {
        umsMemberRepository.updatePassword(telephone,password,authCode);
    }

    @Override
    public UmsMemberVO getCurrentMemberVO() {
        return umsMemberRepository.getCurrentMemberVO();
    }

    @Override
    public UmsMember getCurrentMember() {
        return umsMemberRepository.getCurrentMember();
    }

    @Override
    public void updateIntegration(Long id, Integer integration) {
        umsMemberRepository.updateIntegration(id,integration);
    }

    @Override
    public UserDto loadUserByUsername(String username) {
        return umsMemberRepository.loadUserByUsername(username);
    }

    @Override
    public Map<String,String> login(String username, String password) {
        return umsMemberRepository.login(username,password);
    }
}

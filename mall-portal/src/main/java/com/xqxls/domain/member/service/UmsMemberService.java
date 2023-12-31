package com.xqxls.domain.member.service;

import com.xqxls.api.CommonResult;
import com.xqxls.domain.UserDto;
import com.xqxls.domain.member.model.vo.UmsMemberVO;
import com.xqxls.model.UmsMember;

import java.util.Map;

/**
 * 会员管理Service
 * Created by macro on 2018/8/3.
 */
public interface UmsMemberService {

    /**
     * 根据用户名获取会员
     */
    UmsMemberVO getByUsername(String username);

    /**
     * 根据会员编号获取会员
     */
    UmsMemberVO getById(Long id);

    /**
     * 用户注册
     */
    void register(String username, String password, String telephone, String authCode);

    /**
     * 生成验证码
     */
    String generateAuthCode(String telephone);

    /**
     * 修改密码
     */
    void updatePassword(String telephone, String password, String authCode);

    /**
     * 获取当前登录会员
     */
    UmsMemberVO getCurrentMemberVO();

    /**
     * 获取当前登录会员
     */
    UmsMember getCurrentMember();

    /**
     * 根据会员id修改会员积分
     */
    void updateIntegration(Long id,Integer integration);


    /**
     * 获取用户信息
     */
    UserDto loadUserByUsername(String username);

    /**
     * 登录后获取token
     */
    Map<String,String> login(String username, String password);
}

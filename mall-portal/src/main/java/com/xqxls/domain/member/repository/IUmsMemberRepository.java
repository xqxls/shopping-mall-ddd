package com.xqxls.domain.member.repository;

import com.xqxls.api.CommonResult;
import com.xqxls.domain.UserDto;
import com.xqxls.domain.member.model.vo.UmsMemberVO;
import com.xqxls.model.UmsMember;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/2 15:41
 */
public interface IUmsMemberRepository {

    /**
     * 根据用户名获取会员
     */
    UmsMemberVO getByUsername(String username);

    /**
     * 根据会员编号获取会员
     */
    UmsMemberVO getById(Long id);

    /**
     * 根据会员编号获取会员
     */
    UmsMember getEntityById(Long id);

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

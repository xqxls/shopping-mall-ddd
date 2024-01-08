package com.xqxls.repository.member;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.BCrypt;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.xqxls.api.CommonResult;
import com.xqxls.api.ResultCode;
import com.xqxls.constant.AuthConstant;
import com.xqxls.convert.member.UmsMemberConvert;
import com.xqxls.domain.UserDto;
import com.xqxls.domain.member.model.vo.UmsMemberVO;
import com.xqxls.domain.member.repository.IUmsMemberRepository;
import com.xqxls.exception.Asserts;
import com.xqxls.feign.AuthFeign;
import com.xqxls.mapper.UmsMemberLevelMapper;
import com.xqxls.mapper.UmsMemberMapper;
import com.xqxls.model.UmsMember;
import com.xqxls.model.UmsMemberLevel;
import com.xqxls.redis.UmsMemberCacheService;
import com.xqxls.util.SecurityUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/2 15:42
 */
@Repository
public class UmsMemberRepository implements IUmsMemberRepository {

    @Resource
    private UmsMemberMapper memberMapper;
    @Resource
    private UmsMemberLevelMapper memberLevelMapper;
    @Resource
    private UmsMemberCacheService memberCacheService;
    @Value("${redis.key.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${redis.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;
    @Resource
    private AuthFeign authFeign;
    @Resource
    private HttpServletRequest request;

    @Override
    public UmsMemberVO getByUsername(String username) {
        Example example = new Example(UmsMember.class);
        example.createCriteria().andEqualTo("username",username);
        List<UmsMember> memberList = memberMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(memberList)) {
            UmsMemberVO umsMemberVO = new UmsMemberVO();
            BeanUtils.copyProperties(memberList.get(0),umsMemberVO);
            return umsMemberVO;
//            return UmsMemberConvert.INSTANCE.convertEntityToVO(memberList.get(0));
        }
        return null;
    }

    @Override
    public UmsMemberVO getById(Long id) {
        return UmsMemberConvert.INSTANCE.convertEntityToVO(memberMapper.selectByPrimaryKey(id));
    }

    @Override
    public UmsMember getEntityById(Long id) {
        return memberMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(String username, String password, String telephone, String authCode) {
        //验证验证码
        if(!verifyAuthCode(authCode,telephone)){
            Asserts.fail("验证码错误");
        }
        //查询是否已有该用户
        Example example = new Example(UmsMember.class);
        example.createCriteria().andEqualTo("username",username);
        example.or(example.createCriteria().andEqualTo("phone",telephone));
        List<UmsMember> umsMembers = memberMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(umsMembers)) {
            Asserts.fail("该用户已经存在");
        }
        //没有该用户进行添加操作
        UmsMember umsMember = new UmsMember();
        umsMember.setUsername(username);
        umsMember.setPhone(telephone);
        umsMember.setPassword(BCrypt.hashpw(password));
        umsMember.setCreateTime(new Date());
        umsMember.setStatus(1);
        //获取默认会员等级并设置
        Example levelExample = new Example(UmsMemberLevel.class);
        levelExample.createCriteria().andEqualTo("defaultStatus",1);
        List<UmsMemberLevel> memberLevelList = memberLevelMapper.selectByExample(levelExample);
        if (!CollectionUtils.isEmpty(memberLevelList)) {
            umsMember.setMemberLevelId(memberLevelList.get(0).getId());
        }
        memberMapper.insert(umsMember);
        umsMember.setPassword(null);
    }

    @Override
    public String generateAuthCode(String telephone) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i=0;i<6;i++){
            sb.append(random.nextInt(10));
        }
        memberCacheService.setAuthCode(telephone,sb.toString());
        return sb.toString();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePassword(String telephone, String password, String authCode) {
        Example example = new Example(UmsMember.class);
        example.createCriteria().andEqualTo("phone",telephone);
        List<UmsMember> memberList = memberMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(memberList)){
            Asserts.fail("该账号不存在");
        }
        //验证验证码
        if(!verifyAuthCode(authCode,telephone)){
            Asserts.fail("验证码错误");
        }
        UmsMember umsMember = memberList.get(0);
        umsMember.setPassword(BCrypt.hashpw(password));
        memberMapper.updateByPrimaryKeySelective(umsMember);
        memberCacheService.delMember(umsMember.getId());
    }

    @Override
    public UmsMemberVO getCurrentMemberVO() {

        return UmsMemberConvert.INSTANCE.convertEntityToVO(this.getCurrentMember());
    }

    @Override
    public UmsMember getCurrentMember() {
        UserDto userDto = SecurityUtil.getCurrentUser();
        if(Objects.isNull(userDto)){
            Asserts.fail(ResultCode.UNAUTHORIZED);
        }
        UmsMember member = memberCacheService.getMember(userDto.getId());
        if(member!=null){
            return member;
        }else{
            member = memberMapper.selectByPrimaryKey(userDto.getId());
            memberCacheService.setMember(member);
            return member;
        }
    }

    @Override
    public void updateIntegration(Long id, Integer integration) {
        UmsMember record=new UmsMember();
        record.setId(id);
        record.setIntegration(integration);
        memberMapper.updateByPrimaryKeySelective(record);
        memberCacheService.delMember(id);
    }

    @Override
    public UserDto loadUserByUsername(String username) {
        UmsMemberVO memberVO = getByUsername(username);
        if(memberVO!=null){
            UserDto userDto = new UserDto();
            BeanUtil.copyProperties(memberVO,userDto);
            userDto.setRoles(CollUtil.toList("前台会员"));
            return userDto;
        }
        return null;
    }

    @Override
    public Map<String,String> login(String username, String password) {
        if(StrUtil.isEmpty(username)||StrUtil.isEmpty(password)){
            Asserts.fail("用户名或密码不能为空！");
        }
        Map<String, String> params = new HashMap<>();
        params.put("client_id", AuthConstant.PORTAL_CLIENT_ID);
        params.put("username",username);
        params.put("password",password);
        CommonResult<Map<String,String>> restResult = authFeign.login(params);
        if(ResultCode.SUCCESS.getCode()==restResult.getCode()&&restResult.getData()!=null){
            return restResult.getData();
        }
        return null;
    }

    //对输入的验证码进行校验
    private boolean verifyAuthCode(String authCode, String telephone){
        if(StringUtils.isEmpty(authCode)){
            return false;
        }
        String realAuthCode = memberCacheService.getAuthCode(telephone);
        return authCode.equals(realAuthCode);
    }

}

package com.xqxls.ums.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.xqxls.api.CommonResult;
import com.xqxls.api.ResultCode;
import com.xqxls.domain.UserDto;
import com.xqxls.exception.Asserts;
import com.xqxls.feign.AuthFeign;
import com.xqxls.ums.model.req.UmsAdminReq;
import com.xqxls.ums.model.req.UpdateAdminPasswordReq;
import com.xqxls.ums.model.vo.UmsAdminVO;
import com.xqxls.ums.model.vo.UmsResourceVO;
import com.xqxls.ums.model.vo.UmsRoleVO;
import com.xqxls.ums.repository.IUmsAdminRepository;
import com.xqxls.ums.service.UmsAdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * UmsAdminService实现类
 * Created by xqxls on 2018/4/26.
 */
@Service
public class UmsAdminServiceImpl implements UmsAdminService {

    @Resource
    private IUmsAdminRepository umsAdminRepository;

    @Resource
    private AuthFeign authFeign;

    @Override
    public UmsAdminVO getAdminByUsername(String username) {
        return umsAdminRepository.getAdminByUsername(username);
    }

    @Override
    public UmsAdminVO register(UmsAdminReq umsAdminReq) {
        return umsAdminRepository.register(umsAdminReq);
    }

    @Override
    public UmsAdminVO getItem(Long id) {
        return umsAdminRepository.getItem(id);
    }

    @Override
    public List<UmsAdminVO> list(String keyword, Integer pageSize, Integer pageNum) {
        return umsAdminRepository.list(keyword, pageSize, pageNum);
    }

    @Override
    public int update(Long id, UmsAdminVO umsAdminVO) {
        return umsAdminRepository.update(id,umsAdminVO);
    }

    @Override
    public int delete(Long id) {
        return umsAdminRepository.delete(id);
    }

    @Override
    public int updateRole(Long adminId, List<Long> roleIds) {
        return umsAdminRepository.updateRole(adminId,roleIds);
    }

    @Override
    public List<UmsRoleVO> getRoleList(Long adminId) {
        return umsAdminRepository.getRoleList(adminId);
    }

    @Override
    public List<UmsResourceVO> getResourceList(Long adminId) {
        return umsAdminRepository.getResourceList(adminId);
    }

    @Override
    public int updatePassword(UpdateAdminPasswordReq updateAdminPasswordReq) {
        return umsAdminRepository.updatePassword(updateAdminPasswordReq);
    }

    @Override
    public Map<String, String> login(String username, String password) {
        if(StrUtil.isEmpty(username)||StrUtil.isEmpty(password)){
            Asserts.fail("用户名或密码不能为空！");
        }
        CommonResult<Map<String,String>> restResult = authFeign.login(username,password);
        if(ResultCode.SUCCESS.getCode()==restResult.getCode()&&restResult.getData()!=null){
            return restResult.getData();
        }
        return null;
    }

    @Override
    public UserDto loadUserByUsername(String username) {
        //获取用户信息
        UmsAdminVO adminVO = getAdminByUsername(username);
        if (adminVO != null) {
            List<UmsRoleVO> roleList = getRoleList(adminVO.getId());
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(adminVO,userDto);
            if(CollUtil.isNotEmpty(roleList)){
                List<String> roleStrList = roleList.stream().map(item -> item.getId() + "_" + item.getName()).collect(Collectors.toList());
                userDto.setRoles(roleStrList);
            }
            return userDto;
        }
        return null;
    }
}

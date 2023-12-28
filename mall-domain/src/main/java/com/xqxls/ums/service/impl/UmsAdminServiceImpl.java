package com.xqxls.ums.service.impl;

import com.xqxls.ums.model.req.UmsAdminReq;
import com.xqxls.ums.model.req.UpdateAdminPasswordReq;
import com.xqxls.ums.model.vo.UmsAdminVO;
import com.xqxls.ums.model.vo.UmsResourceVO;
import com.xqxls.ums.model.vo.UmsRoleVO;
import com.xqxls.ums.repository.IUmsAdminRepository;
import com.xqxls.ums.service.UmsAdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * UmsAdminService实现类
 * Created by xqxls on 2018/4/26.
 */
@Service
public class UmsAdminServiceImpl implements UmsAdminService {

    @Resource
    private IUmsAdminRepository umsAdminRepository;


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

}

package com.xqxls.ums.service.impl;

import com.xqxls.ums.model.vo.UmsMenuVO;
import com.xqxls.ums.model.vo.UmsResourceVO;
import com.xqxls.ums.model.vo.UmsRoleVO;
import com.xqxls.ums.repository.IUmsRoleRepository;
import com.xqxls.ums.service.UmsRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 后台角色管理Service实现类
 * Created by xqxls on 2018/9/30.
 */
@Service
public class UmsRoleServiceImpl implements UmsRoleService {

    @Resource
    private IUmsRoleRepository umsRoleRepository;

    @Override
    public int create(UmsRoleVO role) {
        return umsRoleRepository.create(role);
    }

    @Override
    public int update(Long id, UmsRoleVO role) {
        return umsRoleRepository.update(id,role);
    }

    @Override
    public int delete(List<Long> ids) {
        return umsRoleRepository.delete(ids);
    }

    @Override
    public List<UmsRoleVO> list() {
        return umsRoleRepository.list();
    }

    @Override
    public List<UmsRoleVO> list(String keyword, Integer pageSize, Integer pageNum) {
        return umsRoleRepository.list(keyword,pageSize,pageNum);
    }

    @Override
    public List<UmsMenuVO> getMenuList(Long adminId) {
        return umsRoleRepository.getMenuList(adminId);
    }

    @Override
    public List<UmsMenuVO> listMenu(Long roleId) {
        return umsRoleRepository.listMenu(roleId);
    }

    @Override
    public List<UmsResourceVO> listResource(Long roleId) {
        return umsRoleRepository.listResource(roleId);
    }

    @Override
    public int allocMenu(Long roleId, List<Long> menuIds) {
        return umsRoleRepository.allocMenu(roleId,menuIds);
    }

    @Override
    public int allocResource(Long roleId, List<Long> resourceIds) {
        return umsRoleRepository.allocResource(roleId,resourceIds);
    }
}

package com.xqxls.repository;

import com.github.pagehelper.PageHelper;
import com.xqxls.convert.UmsMenuConvert;
import com.xqxls.convert.UmsResourceConvert;
import com.xqxls.convert.UmsRoleConvert;
import com.xqxls.dao.UmsRoleDao;
import com.xqxls.mapper.UmsRoleMapper;
import com.xqxls.mapper.UmsRoleMenuRelationMapper;
import com.xqxls.mapper.UmsRoleResourceRelationMapper;
import com.xqxls.model.*;
import com.xqxls.ums.model.vo.UmsMenuVO;
import com.xqxls.ums.model.vo.UmsResourceVO;
import com.xqxls.ums.model.vo.UmsRoleVO;
import com.xqxls.ums.repository.IUmsRoleRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/27 21:22
 */
@Repository
public class UmsRoleRepository implements IUmsRoleRepository {

    @Resource
    private UmsRoleMapper roleMapper;
    @Resource
    private UmsRoleDao roleDao;
    @Resource
    private UmsRoleMenuRelationMapper roleMenuRelationMapper;
    @Resource
    private UmsRoleResourceRelationMapper roleResourceRelationMapper;
    @Resource
    private UmsResourceRepository umsResourceRepository;

    @Override
    public int create(UmsRoleVO umsRoleVO) {
        UmsRole role = UmsRoleConvert.INSTANCE.umsRoleVOToEntity(umsRoleVO);
        role.setCreateTime(new Date());
        role.setAdminCount(0);
        role.setSort(0);
        return roleMapper.insert(role);
    }

    @Override
    public int update(Long id, UmsRoleVO umsRoleVO) {
        UmsRole role = UmsRoleConvert.INSTANCE.umsRoleVOToEntity(umsRoleVO);
        role.setId(id);
        int count = roleMapper.updateByPrimaryKeySelective(role);
        umsResourceRepository.initResourceRolesMap();
        return count;
    }

    @Override
    public int delete(List<Long> ids) {
        UmsRoleExample example = new UmsRoleExample();
        example.createCriteria().andIdIn(ids);
        int count = roleMapper.deleteByExample(example);
        umsResourceRepository.initResourceRolesMap();
        return count;
    }

    @Override
    public List<UmsRoleVO> list() {
        return UmsRoleConvert.INSTANCE.umsRoleEntityToVOList(roleMapper.selectByExample(new UmsRoleExample()));
    }

    @Override
    public List<UmsRoleVO> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        UmsRoleExample example = new UmsRoleExample();
        if (!StringUtils.isEmpty(keyword)) {
            example.createCriteria().andNameLike("%" + keyword + "%");
        }
        return UmsRoleConvert.INSTANCE.umsRoleEntityToVOList(roleMapper.selectByExample(example));
    }

    @Override
    public List<UmsMenuVO> getMenuList(Long adminId) {
        return UmsMenuConvert.INSTANCE.umsMenuEntityToVOList(roleDao.getMenuList(adminId));
    }

    @Override
    public List<UmsMenuVO> listMenu(Long roleId) {
        return UmsMenuConvert.INSTANCE.umsMenuEntityToVOList(roleDao.getMenuListByRoleId(roleId));
    }

    @Override
    public List<UmsResourceVO> listResource(Long roleId) {
        return UmsResourceConvert.INSTANCE.convertResourceList(roleDao.getResourceListByRoleId(roleId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int allocMenu(Long roleId, List<Long> menuIds) {
        //先删除原有关系
        UmsRoleMenuRelationExample example=new UmsRoleMenuRelationExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        roleMenuRelationMapper.deleteByExample(example);
        //批量插入新关系
        for (Long menuId : menuIds) {
            UmsRoleMenuRelation relation = new UmsRoleMenuRelation();
            relation.setRoleId(roleId);
            relation.setMenuId(menuId);
            roleMenuRelationMapper.insert(relation);
        }
        return menuIds.size();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int allocResource(Long roleId, List<Long> resourceIds) {
        //先删除原有关系
        UmsRoleResourceRelationExample example=new UmsRoleResourceRelationExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        roleResourceRelationMapper.deleteByExample(example);
        //批量插入新关系
        for (Long resourceId : resourceIds) {
            UmsRoleResourceRelation relation = new UmsRoleResourceRelation();
            relation.setRoleId(roleId);
            relation.setResourceId(resourceId);
            roleResourceRelationMapper.insert(relation);
        }
        umsResourceRepository.initResourceRolesMap();
        return resourceIds.size();
    }
}

package com.xqxls.repository.ums;

import com.github.pagehelper.PageHelper;
import com.xqxls.convert.ums.UmsMenuConvert;
import com.xqxls.convert.ums.UmsResourceConvert;
import com.xqxls.convert.ums.UmsRoleConvert;
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
import tk.mybatis.mapper.entity.Example;

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
        Example example = new Example(UmsRole.class);
        example.createCriteria().andIn("id",ids);
        int count = roleMapper.deleteByExample(example);
        umsResourceRepository.initResourceRolesMap();
        return count;
    }

    @Override
    public List<UmsRoleVO> list() {
        return UmsRoleConvert.INSTANCE.umsRoleEntityToVOList(roleMapper.selectByExample(new Example(UmsRole.class)));
    }

    @Override
    public List<UmsRoleVO> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(UmsRole.class);
        if (StringUtils.hasText(keyword)) {
            example.createCriteria().andLike("name","%" + keyword + "%");
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
        Example example=new Example(UmsRoleMenuRelation.class);
        example.createCriteria().andEqualTo("roleId",roleId);
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
        Example example=new Example(UmsRoleResourceRelation.class);
        example.createCriteria().andEqualTo("roleId",roleId);
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

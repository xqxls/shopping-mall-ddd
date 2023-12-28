package com.xqxls.repository.ums;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.xqxls.constant.AuthConstant;
import com.xqxls.convert.ums.UmsResourceConvert;
import com.xqxls.mapper.UmsResourceMapper;
import com.xqxls.mapper.UmsRoleMapper;
import com.xqxls.mapper.UmsRoleResourceRelationMapper;
import com.xqxls.model.*;
import com.xqxls.service.RedisService;
import com.xqxls.ums.model.vo.UmsResourceVO;
import com.xqxls.ums.repository.IUmsResourceRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/27 21:57
 */
@Repository
public class UmsResourceRepository implements IUmsResourceRepository {

    @Resource
    private UmsResourceMapper resourceMapper;
    @Resource
    private UmsRoleMapper roleMapper;
    @Resource
    private UmsRoleResourceRelationMapper roleResourceRelationMapper;
    @Resource
    private RedisService redisService;

    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public int create(UmsResourceVO umsResourceVO) {
        UmsResource umsResource = UmsResourceConvert.INSTANCE.umsResourceVOToEntity(umsResourceVO);
        umsResource.setCreateTime(new Date());
        int count = resourceMapper.insert(umsResource);
        initResourceRolesMap();
        return count;
    }

    @Override
    public int update(Long id, UmsResourceVO umsResourceVO) {
        UmsResource umsResource = UmsResourceConvert.INSTANCE.umsResourceVOToEntity(umsResourceVO);
        umsResource.setId(id);
        int count = resourceMapper.updateByPrimaryKeySelective(umsResource);
        initResourceRolesMap();
        return count;
    }

    @Override
    public UmsResourceVO getItem(Long id) {
        return UmsResourceConvert.INSTANCE.umsResourceEntityToVO(resourceMapper.selectByPrimaryKey(id));
    }

    @Override
    public int delete(Long id) {
        int count = resourceMapper.deleteByPrimaryKey(id);
        initResourceRolesMap();
        return count;
    }

    @Override
    public List<UmsResourceVO> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        UmsResourceExample example = new UmsResourceExample();
        UmsResourceExample.Criteria criteria = example.createCriteria();
        if(categoryId!=null){
            criteria.andCategoryIdEqualTo(categoryId);
        }
        if(StrUtil.isNotEmpty(nameKeyword)){
            criteria.andNameLike('%'+nameKeyword+'%');
        }
        if(StrUtil.isNotEmpty(urlKeyword)){
            criteria.andUrlLike('%'+urlKeyword+'%');
        }
        return UmsResourceConvert.INSTANCE.convertResourceList(resourceMapper.selectByExample(example));
    }

    @Override
    public List<UmsResourceVO> listAll() {
        return UmsResourceConvert.INSTANCE.convertResourceList(resourceMapper.selectByExample(new UmsResourceExample()));
    }

    @Override
    public Map<String, List<String>> initResourceRolesMap() {
        Map<String,List<String>> resourceRoleMap = new TreeMap<>();
        List<UmsResource> resourceList = resourceMapper.selectByExample(new UmsResourceExample());
        List<UmsRole> roleList = roleMapper.selectByExample(new UmsRoleExample());
        List<UmsRoleResourceRelation> relationList = roleResourceRelationMapper.selectByExample(new UmsRoleResourceRelationExample());
        for (UmsResource resource : resourceList) {
            Set<Long> roleIds = relationList.stream().filter(item -> item.getResourceId().equals(resource.getId())).map(UmsRoleResourceRelation::getRoleId).collect(Collectors.toSet());
            List<String> roleNames = roleList.stream().filter(item -> roleIds.contains(item.getId())).map(item -> item.getId() + "_" + item.getName()).collect(Collectors.toList());
            resourceRoleMap.put("/"+applicationName+resource.getUrl(),roleNames);
        }
        redisService.del(AuthConstant.RESOURCE_ROLES_MAP_KEY);
        redisService.hSetAll(AuthConstant.RESOURCE_ROLES_MAP_KEY, resourceRoleMap);
        return resourceRoleMap;
    }
}

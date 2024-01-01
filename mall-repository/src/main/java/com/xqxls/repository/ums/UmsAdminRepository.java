package com.xqxls.repository.ums;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.xqxls.convert.ums.UmsAdminConvert;
import com.xqxls.convert.ums.UmsResourceConvert;
import com.xqxls.convert.ums.UmsRoleConvert;
import com.xqxls.dao.UmsAdminRoleRelationDao;
import com.xqxls.exception.ApiException;
import com.xqxls.mapper.UmsAdminMapper;
import com.xqxls.mapper.UmsAdminRoleRelationMapper;
import com.xqxls.model.UmsAdmin;
import com.xqxls.model.UmsAdminRoleRelation;
import com.xqxls.redis.UmsAdminCacheService;
import com.xqxls.ums.model.req.UmsAdminReq;
import com.xqxls.ums.model.req.UpdateAdminPasswordReq;
import com.xqxls.ums.model.vo.UmsAdminVO;
import com.xqxls.ums.model.vo.UmsResourceVO;
import com.xqxls.ums.model.vo.UmsRoleVO;
import com.xqxls.ums.repository.IUmsAdminRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/27 15:10
 */
@Repository
public class UmsAdminRepository implements IUmsAdminRepository {

    @Resource
    private UmsAdminMapper adminMapper;
    @Resource
    private UmsAdminRoleRelationMapper adminRoleRelationMapper;
    @Resource
    private UmsAdminRoleRelationDao adminRoleRelationDao;
    @Resource
    private UmsAdminCacheService umsAdminCacheService;

    @Override
    public UmsAdminVO getAdminByUsername(String username) {
        Example example = new Example(UmsAdmin.class);
        example.createCriteria().andEqualTo("username",username);
        List<UmsAdmin> adminList = adminMapper.selectByExample(example);
        if (adminList.isEmpty() ) {
            throw new ApiException("用户名不存在");
        }
        if (adminList.size() >1) {
            throw new ApiException("该用户名存在多个用户");
        }
        UmsAdmin umsAdmin = adminList.get(0);
        return UmsAdminConvert.INSTANCE.convertAdmin(umsAdmin);
    }

    @Override
    public UmsAdminVO register(UmsAdminReq umsAdminReq) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdminReq, umsAdmin);
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1);
        //查询是否有相同用户名的用户
        Example example = new Example(UmsAdmin.class);
        example.createCriteria().andEqualTo("username",umsAdmin.getUsername());
        List<UmsAdmin> umsAdminList = adminMapper.selectByExample(example);
        if (!umsAdminList.isEmpty()) {
            throw new ApiException("用户名已存在，请勿重复注册");
        }
        //将密码进行加密操作
        String encodePassword = SaSecureUtil.md5(umsAdmin.getPassword());
        umsAdmin.setPassword(encodePassword);
        adminMapper.insert(umsAdmin);
        return UmsAdminConvert.INSTANCE.convertAdmin(umsAdmin);
    }

    @Override
    public UmsAdminVO getItem(Long id) {
        return UmsAdminConvert.INSTANCE.convertAdmin(adminMapper.selectByPrimaryKey(id));
    }

    @Override
    public List<UmsAdminVO> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(UmsAdmin.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.hasText(keyword)) {
            criteria.andLike("username","%" + keyword + "%");
            example.or(example.createCriteria().andLike("nickName","%" + keyword + "%"));
        }
        return UmsAdminConvert.INSTANCE.convertAdminList(adminMapper.selectByExample(example));
    }

    @Override
    public int update(Long id, UmsAdminVO umsAdminVO) {
        umsAdminCacheService.delAdmin(id);
        umsAdminVO.setId(id);
        UmsAdmin rawAdmin = adminMapper.selectByPrimaryKey(id);
        if(rawAdmin.getPassword().equals(SaSecureUtil.md5(umsAdminVO.getPassword()))){
            //与原加密密码相同的不需要修改
            umsAdminVO.setPassword(null);
        }else{
            //与原加密密码不同的需要加密修改
            if(StrUtil.isEmpty(umsAdminVO.getPassword())){
                umsAdminVO.setPassword(null);
            }else{
                umsAdminVO.setPassword(SaSecureUtil.md5(umsAdminVO.getPassword()));
            }
        }
        UmsAdmin queryUmsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdminVO,queryUmsAdmin);
        return adminMapper.updateByPrimaryKeySelective(queryUmsAdmin);
    }

    @Override
    public int delete(Long id) {
        umsAdminCacheService.delAdmin(id);
        return adminMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateRole(Long adminId, List<Long> roleIds) {
        int count = roleIds == null ? 0 : roleIds.size();
        //先删除原来的关系
        Example example = new Example(UmsAdminRoleRelation.class);
        example.createCriteria().andEqualTo("adminId",adminId);
        adminRoleRelationMapper.deleteByExample(example);
        //建立新关系
        if (!CollectionUtils.isEmpty(roleIds)) {
            List<UmsAdminRoleRelation> list = new ArrayList<>();
            for (Long roleId : roleIds) {
                UmsAdminRoleRelation roleRelation = new UmsAdminRoleRelation();
                roleRelation.setAdminId(adminId);
                roleRelation.setRoleId(roleId);
                list.add(roleRelation);
            }
            adminRoleRelationDao.insertList(list);
        }
        return count;
    }

    @Override
    public List<UmsRoleVO> getRoleList(Long adminId) {
        return UmsRoleConvert.INSTANCE.umsRoleEntityToVOList(adminRoleRelationDao.getRoleList(adminId));
    }

    @Override
    public List<UmsResourceVO> getResourceList(Long adminId) {
        return UmsResourceConvert.INSTANCE.convertResourceList(adminRoleRelationDao.getResourceList(adminId));
    }

    @Override
    public int updatePassword(UpdateAdminPasswordReq updateAdminPasswordReq) {
        if(StrUtil.isEmpty(updateAdminPasswordReq.getUsername())
                ||StrUtil.isEmpty(updateAdminPasswordReq.getOldPassword())
                ||StrUtil.isEmpty(updateAdminPasswordReq.getNewPassword())){
            return -1;
        }
        Example example = new Example(UmsAdmin.class);
        example.createCriteria().andEqualTo("username",updateAdminPasswordReq.getUsername());
        List<UmsAdmin> adminList = adminMapper.selectByExample(example);
        if(CollUtil.isEmpty(adminList)){
            return -2;
        }
        UmsAdmin umsAdmin = adminList.get(0);
        if(!SaSecureUtil.md5(updateAdminPasswordReq.getOldPassword()).equals(umsAdmin.getPassword())){
            return -3;
        }
        umsAdmin.setPassword(SaSecureUtil.md5(updateAdminPasswordReq.getNewPassword()));
        adminMapper.updateByPrimaryKey(umsAdmin);
        umsAdminCacheService.delAdmin(umsAdmin.getId());
        return 1;
    }

}

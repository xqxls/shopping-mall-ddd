package com.xqxls.repository.ums;

import com.github.pagehelper.PageHelper;
import com.xqxls.convert.ums.UmsMenuConvert;
import com.xqxls.mapper.UmsMenuMapper;
import com.xqxls.model.UmsMenu;
import com.xqxls.ums.model.vo.UmsMenuVO;
import com.xqxls.ums.repository.IUmsMenuRepository;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/27 20:43
 */
@Repository
public class UmsMenuRepository implements IUmsMenuRepository {

    @Resource
    private UmsMenuMapper menuMapper;

    @Override
    public int create(UmsMenuVO UmsMenuVO) {
        UmsMenu umsMenu = UmsMenuConvert.INSTANCE.umsMenuVOToEntity(UmsMenuVO);
        umsMenu.setCreateTime(new Date());
        updateLevel(umsMenu);
        return menuMapper.insert(umsMenu);
    }

    /**
     * 修改菜单层级
     */
    private void updateLevel(UmsMenu umsMenu) {
        if (umsMenu.getParentId() == 0) {
            //没有父菜单时为一级菜单
            umsMenu.setLevel(0);
        } else {
            //有父菜单时选择根据父菜单level设置
            UmsMenu parentMenu = menuMapper.selectByPrimaryKey(umsMenu.getParentId());
            if (parentMenu != null) {
                umsMenu.setLevel(parentMenu.getLevel() + 1);
            } else {
                umsMenu.setLevel(0);
            }
        }
    }

    @Override
    public int update(Long id, UmsMenuVO UmsMenuVO) {
        UmsMenu umsMenu = UmsMenuConvert.INSTANCE.umsMenuVOToEntity(UmsMenuVO);
        umsMenu.setId(id);
        updateLevel(umsMenu);
        return menuMapper.updateByPrimaryKeySelective(umsMenu);
    }

    @Override
    public UmsMenuVO getItem(Long id) {
        return UmsMenuConvert.INSTANCE.umsMenuEntityToVO(menuMapper.selectByPrimaryKey(id));
    }

    @Override
    public int delete(Long id) {
        return menuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<UmsMenuVO> list(Long parentId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(UmsMenu.class);
        example.setOrderByClause("sort desc");
        example.createCriteria().andEqualTo("parentId",parentId);
        return UmsMenuConvert.INSTANCE.umsMenuEntityToVOList(menuMapper.selectByExample(example));
    }


    @Override
    public List<UmsMenuVO> list() {
        List<UmsMenu> menuList = menuMapper.selectByExample(new Example(UmsMenu.class));
        return UmsMenuConvert.INSTANCE.umsMenuEntityToVOList(menuList);
    }

    @Override
    public int updateHidden(Long id, Integer hidden) {
        UmsMenu umsMenu = new UmsMenu();
        umsMenu.setId(id);
        umsMenu.setHidden(hidden);
        return menuMapper.updateByPrimaryKeySelective(umsMenu);
    }
}

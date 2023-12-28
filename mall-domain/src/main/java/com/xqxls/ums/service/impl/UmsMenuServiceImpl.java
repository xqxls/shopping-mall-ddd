package com.xqxls.ums.service.impl;

import com.xqxls.ums.model.res.UmsMenuNodeResult;
import com.xqxls.ums.model.vo.UmsMenuVO;
import com.xqxls.ums.repository.IUmsMenuRepository;
import com.xqxls.ums.service.UmsMenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 后台菜单管理Service实现类
 * Created by xqxls on 2020/2/2.
 */
@Service
public class UmsMenuServiceImpl implements UmsMenuService {
    @Resource
    private IUmsMenuRepository umsMenuRepository;

    @Override
    public int create(UmsMenuVO umsMenuVO) {
        return umsMenuRepository.create(umsMenuVO);
    }

    @Override
    public int update(Long id, UmsMenuVO umsMenuVO) {
        return umsMenuRepository.update(id,umsMenuVO);
    }

    @Override
    public UmsMenuVO getItem(Long id) {
        return umsMenuRepository.getItem(id);
    }

    @Override
    public int delete(Long id) {
        return umsMenuRepository.delete(id);
    }

    @Override
    public List<UmsMenuVO> list(Long parentId, Integer pageSize, Integer pageNum) {
        return umsMenuRepository.list(parentId,pageSize,pageNum);
    }

    @Override
    public List<UmsMenuVO> list() {
        return umsMenuRepository.list();
    }

    @Override
    public int updateHidden(Long id, Integer hidden) {
        return umsMenuRepository.updateHidden(id,hidden);
    }

    @Override
    public List<UmsMenuNodeResult> treeList() {
        List<UmsMenuVO> umsMenuVOList = umsMenuRepository.list();
        return umsMenuVOList.stream()
                .filter(menu -> menu.getParentId().equals(0L))
                .map(menu -> covertMenuNode(menu, umsMenuVOList)).collect(Collectors.toList());
    }

    /**
     * 将UmsMenu转化为UmsMenuNode并设置children属性
     */
    private UmsMenuNodeResult covertMenuNode(UmsMenuVO umsMenuVO, List<UmsMenuVO> umsMenuVOList) {
        UmsMenuNodeResult node = new UmsMenuNodeResult();
        BeanUtils.copyProperties(umsMenuVO, node);
        List<UmsMenuNodeResult> children = umsMenuVOList.stream()
                .filter(subMenu -> subMenu.getParentId().equals(umsMenuVO.getId()))
                .map(subMenu -> covertMenuNode(subMenu, umsMenuVOList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }
}

package com.xqxls.ums.repository;

import com.xqxls.ums.model.vo.UmsResourceVO;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/27 14:39
 */
public interface IUmsResourceRepository {

    /**
     * 添加资源
     */
    int create(UmsResourceVO umsResourceVO);

    /**
     * 修改资源
     */
    int update(Long id, UmsResourceVO umsResourceVO);

    /**
     * 获取资源详情
     */
    UmsResourceVO getItem(Long id);

    /**
     * 删除资源
     */
    int delete(Long id);

    /**
     * 分页查询资源
     */
    List<UmsResourceVO> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum);

    /**
     * 查询全部资源
     */
    List<UmsResourceVO> listAll();

    /**
     * 初始化资源角色规则
     */
    Map<String,List<String>> initResourceRolesMap();
}

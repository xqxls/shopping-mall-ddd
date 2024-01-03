package com.xqxls.domain.member.repository;

import com.xqxls.domain.member.model.vo.UmsMemberReceiveAddressVO;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/2 15:24
 */
public interface IUmsMemberReceiveAddressRepository {

    /**
     * 添加收货地址
     */
    int add(UmsMemberReceiveAddressVO umsMemberReceiveAddressVO);

    /**
     * 删除收货地址
     * @param id 地址表的id
     */
    int delete(Long id);

    /**
     * 修改收货地址
     * @param id 地址表的id
     * @param umsMemberReceiveAddressVO 修改的收货地址信息
     */
    int update(Long id, UmsMemberReceiveAddressVO umsMemberReceiveAddressVO);

    /**
     * 返回当前用户的收货地址
     */
    List<UmsMemberReceiveAddressVO> list();

    /**
     * 获取地址详情
     * @param id 地址id
     */
    UmsMemberReceiveAddressVO getItem(Long id);
}

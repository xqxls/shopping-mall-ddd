package com.xqxls.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 通过mapper 注意，该接口不能被Mybatis扫描到，否则会出错
 *
 * @Author: huzhuo
 * @Date: Created in 2023/4/25 22:10
 */
public interface TkBaseMapper<T> extends Mapper<T>, MySqlMapper<T>, ExtMapper<T> {

}
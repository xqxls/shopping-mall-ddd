package com.xqxls.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatis相关配置
 * Created by xqxls on 2019/4/8.
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"com.xqxls.mapper","com.xqxls.dao"})
public class MyBatisConfig {

}

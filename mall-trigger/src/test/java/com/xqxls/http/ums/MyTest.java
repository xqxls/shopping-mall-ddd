package com.xqxls.http.ums;

import com.xqxls.mapper.UmsAdminMapper;
import com.xqxls.model.UmsAdmin;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/18 14:12
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyTest {

    @Resource
    private UmsAdminMapper umsAdminMapper;

    @Test
    @Transactional
    public void test(){
        UmsAdmin umsAdminVO = umsAdminMapper.selectByPrimaryKey(1L);
        log.info("更新前状态 is {}",umsAdminVO.getStatus());
        umsAdminVO.setStatus(2);

        UmsAdmin cur = umsAdminMapper.selectByPrimaryKey(1L);
        log.info("当前状态 is {}",cur.getStatus());
    }
}

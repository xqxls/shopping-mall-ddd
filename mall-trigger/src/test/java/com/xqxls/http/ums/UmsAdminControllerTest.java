package com.xqxls.http.ums;


import com.alibaba.fastjson.JSON;
import com.xqxls.convert.ums.UmsAdminConvert;
import com.xqxls.model.UmsAdmin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UmsAdminControllerTest {
    @Test
    public void getItem() {

        UmsAdmin admin = new UmsAdmin();
        admin.setId(11L);
        System.out.println(JSON.toJSONString(UmsAdminConvert.INSTANCE.convertAdmin(admin)));
    }
}
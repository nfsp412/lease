package org.asuka.lease.web.admin.mapper;

import jakarta.annotation.Resource;
import org.asuka.lease.model.entity.SystemUser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class LoginMapperTest {

    @Resource
    LoginMapper loginMapper;

    @Test
    void getSystemUserByUsername() {
        SystemUser admin = loginMapper.getSystemUserByUsername("admin");
        System.out.println(admin);
    }
}
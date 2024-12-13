package org.asuka.lease.web.admin.mapper;

import org.asuka.lease.model.entity.SystemUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoginMapperTest {

    @Autowired
    LoginMapper loginMapper;

    @Test
    void getSystemUserByUsername() {
        SystemUser admin = loginMapper.getSystemUserByUsername("admin");
        System.out.println(admin);
    }
}
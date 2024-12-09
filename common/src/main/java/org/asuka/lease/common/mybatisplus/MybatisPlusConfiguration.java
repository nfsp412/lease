package org.asuka.lease.common.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("org.asuka.lease.web.*.mapper") //这里声明的路径是web-admin和web-app下的路径,所以是*
public class MybatisPlusConfiguration {
}

package org.asuka.lease.common.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 实现自动填充create time字段以及update time字段
 */
//@Component //交给IOC管理,或者在configuration配置类里面使用bean注解注入
public class MybatisMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        //插入时,设置create time和update time字段
        this.strictInsertFill(metaObject,"createTime", Date.class,new Date());
        this.strictUpdateFill(metaObject,"updateTime",Date.class,new Date());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //更新时,设置update time字段
        this.strictUpdateFill(metaObject,"updateTime",Date.class,new Date());
    }
    
}

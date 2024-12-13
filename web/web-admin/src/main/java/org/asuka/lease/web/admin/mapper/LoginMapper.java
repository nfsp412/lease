package org.asuka.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.asuka.lease.model.entity.SystemUser;


public interface LoginMapper extends BaseMapper<SystemUser> {
    SystemUser getSystemUserByUsername(String username);
}

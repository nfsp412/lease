package org.asuka.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.asuka.lease.model.entity.SystemUser;
import org.asuka.lease.web.admin.vo.system.user.SystemUserItemVo;
import org.asuka.lease.web.admin.vo.system.user.SystemUserQueryVo;

/**
* @author liubo
*  针对表【system_user(员工信息表)】的数据库操作Mapper
*
*  com.atguigu.lease.model.SystemUser
*/
public interface SystemUserMapper extends BaseMapper<SystemUser> {

    IPage<SystemUserItemVo> getSystemUserItemVoPage(Page<SystemUserItemVo> page, SystemUserQueryVo queryVo);
}





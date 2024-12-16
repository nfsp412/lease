package org.asuka.lease.web.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.asuka.lease.model.entity.SystemUser;
import org.asuka.lease.web.admin.vo.system.user.SystemUserInfoVo;
import org.asuka.lease.web.admin.vo.system.user.SystemUserItemVo;
import org.asuka.lease.web.admin.vo.system.user.SystemUserQueryVo;

/**
* @author liubo
*  针对表【system_user(员工信息表)】的数据库操作Service
*
*/
public interface SystemUserService extends IService<SystemUser> {

    IPage<SystemUserItemVo> getSystemUserItemVoPage(Page<SystemUserItemVo> page, SystemUserQueryVo queryVo);

    void deleteSystemUserById(Long id);

    SystemUserItemVo getSystemUserItemVoById(Long id);

    SystemUserInfoVo getSystemUserInfoVoById(Long userId);
}

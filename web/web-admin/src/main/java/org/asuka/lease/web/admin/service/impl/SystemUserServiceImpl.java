package org.asuka.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.asuka.lease.common.exception.SystemUserException;
import org.asuka.lease.common.result.ResultCodeEnum;
import org.asuka.lease.model.entity.SystemUser;
import org.asuka.lease.model.enums.BaseStatus;
import org.asuka.lease.web.admin.mapper.SystemUserMapper;
import org.asuka.lease.web.admin.service.SystemUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.asuka.lease.web.admin.vo.system.user.SystemUserInfoVo;
import org.asuka.lease.web.admin.vo.system.user.SystemUserItemVo;
import org.asuka.lease.web.admin.vo.system.user.SystemUserQueryVo;
import org.springframework.stereotype.Service;

/**
 * @author liubo
 *  针对表【system_user(员工信息表)】的数据库操作Service实现
 *
 */
@Service
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser>
        implements SystemUserService {
    @Resource
    private SystemUserMapper systemUserMapper;

    @Override
    public IPage<SystemUserItemVo> getSystemUserItemVoPage(Page<SystemUserItemVo> page, SystemUserQueryVo queryVo) {
        return systemUserMapper.getSystemUserItemVoPage(page,queryVo);
    }

    @Override
    public void deleteSystemUserById(Long id) {
        LambdaQueryWrapper<SystemUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SystemUser::getId,id);
        wrapper.eq(SystemUser::getStatus, BaseStatus.ENABLE);
        Long counts = systemUserMapper.selectCount(wrapper);
        if (counts > 0) {
            //代表该用户是正常状态不应该删除
            throw new SystemUserException(ResultCodeEnum.ADMIN_SYS_USER_DELETE_ERROR);
        }
        //安全删除
        systemUserMapper.deleteById(id);
    }

    @Override
    public SystemUserItemVo getSystemUserItemVoById(Long id) {
        return systemUserMapper.getSystemUserItemVoById(id);

    }

    @Override
    public SystemUserInfoVo getSystemUserInfoVoById(Long userId) {
        return systemUserMapper.getSystemUserInfoVoById(userId);
    }
}





package org.asuka.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.asuka.lease.common.exception.SystemUserException;
import org.asuka.lease.common.result.ResultCodeEnum;
import org.asuka.lease.model.entity.SystemPost;
import org.asuka.lease.model.enums.BaseStatus;
import org.asuka.lease.web.admin.service.SystemPostService;
import org.asuka.lease.web.admin.mapper.SystemPostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author liubo
*  针对表【system_post(岗位信息表)】的数据库操作Service实现
*
*/
@Service
public class SystemPostServiceImpl extends ServiceImpl<SystemPostMapper, SystemPost>
    implements SystemPostService{
    @Resource
    private SystemPostMapper systemPostMapper;

    @Override
    public void deleteSystemPostById(Long id) {
        //根据id查询状态
        LambdaQueryWrapper<SystemPost> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SystemPost::getId,id);
        wrapper.eq(SystemPost::getStatus, BaseStatus.ENABLE);
        Long counts = systemPostMapper.selectCount(wrapper);
        if (counts > 0){
            //无法删除抛出异常
            throw new SystemUserException(ResultCodeEnum.ADMIN_SYS_USER_DELETE_ERROR);
        }
        systemPostMapper.deleteById(id);
    }
}





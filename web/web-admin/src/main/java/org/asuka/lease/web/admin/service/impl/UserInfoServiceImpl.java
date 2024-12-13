package org.asuka.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.asuka.lease.model.entity.UserInfo;
import org.asuka.lease.web.admin.service.UserInfoService;
import org.asuka.lease.web.admin.mapper.UserInfoMapper;
import org.springframework.stereotype.Service;

/**
 * @author liubo
 * 针对表【user_info(用户信息表)】的数据库操作Service实现
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
        implements UserInfoService {
}





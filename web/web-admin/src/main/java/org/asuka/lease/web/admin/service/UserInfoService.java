package org.asuka.lease.web.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.asuka.lease.model.entity.UserInfo;
import org.asuka.lease.web.admin.vo.user.UserInfoQueryVo;

/**
* @author liubo
*  针对表【user_info(用户信息表)】的数据库操作Service
*
*/
public interface UserInfoService extends IService<UserInfo> {

}

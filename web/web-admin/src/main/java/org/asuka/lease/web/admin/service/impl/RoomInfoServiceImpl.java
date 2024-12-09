package org.asuka.lease.web.admin.service.impl;

import org.asuka.lease.model.entity.RoomInfo;
import org.asuka.lease.web.admin.mapper.RoomInfoMapper;
import org.asuka.lease.web.admin.service.RoomInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author liubo
 *  针对表【room_info(房间信息表)】的数据库操作Service实现
 *
 */
@Service
public class RoomInfoServiceImpl extends ServiceImpl<RoomInfoMapper, RoomInfo>
        implements RoomInfoService {

}





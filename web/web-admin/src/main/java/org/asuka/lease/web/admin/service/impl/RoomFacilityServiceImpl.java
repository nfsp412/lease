package org.asuka.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.asuka.lease.model.entity.RoomFacility;
import org.asuka.lease.web.admin.service.RoomFacilityService;
import org.asuka.lease.web.admin.mapper.RoomFacilityMapper;
import org.springframework.stereotype.Service;

/**
* @author liubo
*  针对表【room_facility(房间&配套关联表)】的数据库操作Service实现
*
*/
@Service
public class RoomFacilityServiceImpl extends ServiceImpl<RoomFacilityMapper, RoomFacility>
    implements RoomFacilityService{

}





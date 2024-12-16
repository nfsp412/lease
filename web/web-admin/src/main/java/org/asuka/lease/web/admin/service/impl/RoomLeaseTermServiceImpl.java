package org.asuka.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.asuka.lease.model.entity.RoomLeaseTerm;
import org.asuka.lease.web.admin.service.RoomLeaseTermService;
import org.asuka.lease.web.admin.mapper.RoomLeaseTermMapper;
import org.springframework.stereotype.Service;

/**
 * @author liubo
 *  针对表【room_lease_term(房间租期管理表)】的数据库操作Service实现
 *
 */
@Service
public class RoomLeaseTermServiceImpl extends ServiceImpl<RoomLeaseTermMapper, RoomLeaseTerm>
        implements RoomLeaseTermService {

}





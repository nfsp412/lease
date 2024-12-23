package org.asuka.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.asuka.lease.model.entity.RoomLabel;
import org.asuka.lease.web.admin.service.RoomLabelService;
import org.asuka.lease.web.admin.mapper.RoomLabelMapper;
import org.springframework.stereotype.Service;

/**
 * @author liubo
 *  针对表【room_label(房间&标签关联表)】的数据库操作Service实现
 *
 */
@Service
public class RoomLabelServiceImpl extends ServiceImpl<RoomLabelMapper, RoomLabel>
        implements RoomLabelService {

}





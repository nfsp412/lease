package org.asuka.lease.web.admin.service.impl;

import org.asuka.lease.model.entity.ViewAppointment;
import org.asuka.lease.web.admin.mapper.ViewAppointmentMapper;
import org.asuka.lease.web.admin.service.ViewAppointmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author liubo
 *  针对表【view_appointment(预约看房信息表)】的数据库操作Service实现
 *
 */
@Service
public class ViewAppointmentServiceImpl extends ServiceImpl<ViewAppointmentMapper, ViewAppointment>
        implements ViewAppointmentService {

}





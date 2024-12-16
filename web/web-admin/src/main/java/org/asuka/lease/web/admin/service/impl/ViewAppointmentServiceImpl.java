package org.asuka.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.asuka.lease.model.entity.ViewAppointment;
import org.asuka.lease.web.admin.mapper.ViewAppointmentMapper;
import org.asuka.lease.web.admin.service.ViewAppointmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.asuka.lease.web.admin.vo.appointment.AppointmentQueryVo;
import org.asuka.lease.web.admin.vo.appointment.AppointmentVo;
import org.springframework.stereotype.Service;

/**
 * @author liubo
 *  针对表【view_appointment(预约看房信息表)】的数据库操作Service实现
 *
 */
@Service
public class ViewAppointmentServiceImpl extends ServiceImpl<ViewAppointmentMapper, ViewAppointment>
        implements ViewAppointmentService {
    @Resource
    private ViewAppointmentMapper viewAppointmentMapper;

    @Override
    public IPage<AppointmentVo> getAppointmentVoPage(Page<AppointmentVo> page, AppointmentQueryVo queryVo) {
        return viewAppointmentMapper.getAppointmentVoPage(page,queryVo);
    }
}





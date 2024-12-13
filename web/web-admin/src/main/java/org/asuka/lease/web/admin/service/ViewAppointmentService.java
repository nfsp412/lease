package org.asuka.lease.web.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.asuka.lease.model.entity.ViewAppointment;
import org.asuka.lease.web.admin.vo.appointment.AppointmentQueryVo;
import org.asuka.lease.web.admin.vo.appointment.AppointmentVo;

/**
* @author liubo
*  针对表【view_appointment(预约看房信息表)】的数据库操作Service
*
*/
public interface ViewAppointmentService extends IService<ViewAppointment> {

    IPage<AppointmentVo> getAppointmentVoPage(Page<AppointmentVo> page, AppointmentQueryVo queryVo);
}

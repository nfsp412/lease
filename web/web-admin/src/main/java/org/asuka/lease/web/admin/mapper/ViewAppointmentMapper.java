package org.asuka.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.asuka.lease.model.entity.ViewAppointment;
import org.asuka.lease.web.admin.vo.appointment.AppointmentQueryVo;
import org.asuka.lease.web.admin.vo.appointment.AppointmentVo;

/**
* @author liubo
*  针对表【view_appointment(预约看房信息表)】的数据库操作Mapper
*
*  com.atguigu.lease.model.ViewAppointment
*/
public interface ViewAppointmentMapper extends BaseMapper<ViewAppointment> {

    IPage<AppointmentVo> getAppointmentVoPage(Page<AppointmentVo> page, AppointmentQueryVo queryVo);
}





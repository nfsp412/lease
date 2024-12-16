package org.asuka.lease.web.admin.controller.lease;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.asuka.lease.common.result.Result;
import org.asuka.lease.model.entity.ViewAppointment;
import org.asuka.lease.model.enums.AppointmentStatus;
import org.asuka.lease.web.admin.service.ViewAppointmentService;
import org.asuka.lease.web.admin.vo.appointment.AppointmentQueryVo;
import org.asuka.lease.web.admin.vo.appointment.AppointmentVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Tag(name = "预约看房管理")
@RequestMapping("/admin/appointment")
@RestController
public class ViewAppointmentController {
    @Autowired
    private ViewAppointmentService viewAppointmentService;

    @Operation(summary = "分页查询预约信息")
    @GetMapping("page")
    public Result<IPage<AppointmentVo>> page(@RequestParam long current,
                                             @RequestParam long size,
                                             AppointmentQueryVo queryVo) {
        Page<AppointmentVo> page = new Page<>(current,size);
        IPage<AppointmentVo> appointmentVoPage =viewAppointmentService.getAppointmentVoPage(page,queryVo);
        return Result.ok(appointmentVoPage);
    }

    @Operation(summary = "根据id更新预约状态")
    @PostMapping("updateStatusById")
    public Result updateStatusById(@RequestParam Long id,
                                   @RequestParam AppointmentStatus status) {
        LambdaUpdateWrapper<ViewAppointment> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(ViewAppointment::getId,id);
        wrapper.set(ViewAppointment::getAppointmentStatus,status);
        viewAppointmentService.update(wrapper);
        return Result.ok();
    }

}

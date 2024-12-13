package org.asuka.lease.web.admin.schedule;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.asuka.lease.model.entity.LeaseAgreement;
import org.asuka.lease.model.enums.LeaseStatus;
import org.asuka.lease.web.admin.service.LeaseAgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduledTasks {
    @Autowired
    private LeaseAgreementService leaseAgreementService;

    @Scheduled(cron = "0 0 0 * * *")
    public void checkLeaseStatus(){
        //租约的结束日期如果<=当前日期,则代表已经到期了
        LambdaUpdateWrapper<LeaseAgreement> wrapper = new LambdaUpdateWrapper<>();
        wrapper.le(LeaseAgreement::getLeaseEndDate,new Date());
        //只修改那些已经出租的房间的租约状态
        wrapper.in(LeaseAgreement::getStatus,LeaseStatus.SIGNED,LeaseStatus.WITHDRAWING);
        wrapper.set(LeaseAgreement::getStatus, LeaseStatus.EXPIRED);
        leaseAgreementService.update(wrapper);
    }

//    @Scheduled(cron = "* * * * * *")
//    public void test() {
//        System.out.println(new Date());
//    }
}

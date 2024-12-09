package org.asuka.lease.web.admin.service.impl;

import org.asuka.lease.model.entity.LeaseAgreement;
import org.asuka.lease.web.admin.mapper.LeaseAgreementMapper;
import org.asuka.lease.web.admin.service.LeaseAgreementService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author liubo
 *  针对表【lease_agreement(租约信息表)】的数据库操作Service实现
 *
 */
@Service
public class LeaseAgreementServiceImpl extends ServiceImpl<LeaseAgreementMapper, LeaseAgreement>
        implements LeaseAgreementService {

}





package org.asuka.lease.web.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.asuka.lease.model.entity.LeaseAgreement;
import org.asuka.lease.web.admin.vo.agreement.AgreementQueryVo;
import org.asuka.lease.web.admin.vo.agreement.AgreementVo;

/**
* @author liubo
*  针对表【lease_agreement(租约信息表)】的数据库操作Service
*
*/
public interface LeaseAgreementService extends IService<LeaseAgreement> {

    IPage<AgreementVo> getAgreementVoPage(Page<AgreementVo> page, AgreementQueryVo queryVo);

    void removeLeaseAggrementById(Long id);

    AgreementVo getAgreementVoById(Long id);
}

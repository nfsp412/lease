package org.asuka.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.asuka.lease.model.entity.LeaseAgreement;
import org.asuka.lease.web.admin.vo.agreement.AgreementQueryVo;
import org.asuka.lease.web.admin.vo.agreement.AgreementVo;

/**
* @author liubo
*  针对表【lease_agreement(租约信息表)】的数据库操作Mapper
*
*  com.atguigu.lease.model.LeaseAgreement
*/
public interface LeaseAgreementMapper extends BaseMapper<LeaseAgreement> {

    IPage<AgreementVo> getAgreementVoPage(Page<AgreementVo> page, AgreementQueryVo queryVo);

    AgreementVo getAgreementVoById(Long id);
}





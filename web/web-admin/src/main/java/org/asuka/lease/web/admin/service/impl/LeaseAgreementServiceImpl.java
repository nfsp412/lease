package org.asuka.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.asuka.lease.common.exception.RoomException;
import org.asuka.lease.common.result.ResultCodeEnum;
import org.asuka.lease.model.entity.LeaseAgreement;
import org.asuka.lease.model.enums.LeaseStatus;
import org.asuka.lease.web.admin.mapper.LeaseAgreementMapper;
import org.asuka.lease.web.admin.service.LeaseAgreementService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.asuka.lease.web.admin.vo.agreement.AgreementQueryVo;
import org.asuka.lease.web.admin.vo.agreement.AgreementVo;
import org.springframework.stereotype.Service;

/**
 * @author liubo
 *  针对表【lease_agreement(租约信息表)】的数据库操作Service实现
 *
 */
@Service
public class LeaseAgreementServiceImpl extends ServiceImpl<LeaseAgreementMapper, LeaseAgreement>
        implements LeaseAgreementService {
    @Resource
    private LeaseAgreementMapper leaseAgreementMapper;

    @Override
    public IPage<AgreementVo> getAgreementVoPage(Page<AgreementVo> page, AgreementQueryVo queryVo) {
        return leaseAgreementMapper.getAgreementVoPage(page,queryVo);
    }

    @Override
    public void removeLeaseAggrementById(Long id) {
        LambdaQueryWrapper<LeaseAgreement> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(LeaseAgreement::getId,id);
        wrapper.in(LeaseAgreement::getStatus,LeaseStatus.SIGNED,LeaseStatus.WITHDRAWING);
        Long counts = leaseAgreementMapper.selectCount(wrapper);
        if (counts > 0){
            //证明该房间已经租出去,不应该删除该条租约记录
            throw new RoomException(ResultCodeEnum.ADMIN_ROOM_DELETE_ERROR);
        }
        leaseAgreementMapper.deleteById(id);
    }

    @Override
    public AgreementVo getAgreementVoById(Long id) {
        return leaseAgreementMapper.getAgreementVoById(id);
    }
}





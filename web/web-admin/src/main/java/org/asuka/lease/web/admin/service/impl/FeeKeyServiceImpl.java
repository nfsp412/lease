package org.asuka.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.asuka.lease.model.entity.FeeKey;
import org.asuka.lease.model.entity.FeeValue;
import org.asuka.lease.web.admin.mapper.FeeKeyMapper;
import org.asuka.lease.web.admin.service.FeeKeyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.asuka.lease.web.admin.service.FeeValueService;
import org.asuka.lease.web.admin.vo.fee.FeeKeyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author liubo
 *  针对表【fee_key(杂项费用名称表)】的数据库操作Service实现
 *
 */
@Service
public class FeeKeyServiceImpl extends ServiceImpl<FeeKeyMapper, FeeKey>
        implements FeeKeyService {
    @Autowired
    private FeeKeyMapper feeKeyMapper;

    @Autowired
    private FeeValueService feeValueService;

    @Override
    public List<FeeKeyVo> getFeeKeyVoList() {
        return feeKeyMapper.getFeeKeyVoList();
    }

    @Transactional
    @Override
    public boolean deleteFeeKeyAndValue(Long feeKeyId) {
        boolean flag1 = this.removeById(feeKeyId);
        LambdaQueryWrapper<FeeValue> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FeeValue::getFeeKeyId, feeKeyId);
        boolean flag2 = feeValueService.remove(wrapper);
        if (flag1 && flag2){
            return true;
        }else {
            return false;
        }
    }
}





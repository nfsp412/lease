package org.asuka.lease.web.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.asuka.lease.model.entity.FeeKey;
import org.asuka.lease.web.admin.vo.fee.FeeKeyVo;

import java.util.List;


/**
* @author liubo
*  针对表【fee_key(杂项费用名称表)】的数据库操作Service
*
*/
public interface FeeKeyService extends IService<FeeKey> {

    List<FeeKeyVo> getFeeKeyVoList();

    void deleteFeeKeyAndValue(Long feeKeyId);
}

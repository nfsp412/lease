package org.asuka.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.asuka.lease.model.entity.PaymentType;
import org.asuka.lease.web.admin.service.PaymentTypeService;
import org.asuka.lease.web.admin.mapper.PaymentTypeMapper;
import org.springframework.stereotype.Service;

/**
 * @author liubo
 *  针对表【payment_type(支付方式表)】的数据库操作Service实现
 *
 */
@Service
public class PaymentTypeServiceImpl extends ServiceImpl<PaymentTypeMapper, PaymentType>
        implements PaymentTypeService {

}





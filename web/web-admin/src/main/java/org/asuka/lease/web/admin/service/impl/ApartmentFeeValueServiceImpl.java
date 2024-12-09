package org.asuka.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.asuka.lease.model.entity.ApartmentFeeValue;
import org.asuka.lease.web.admin.mapper.ApartmentFeeValueMapper;
import org.asuka.lease.web.admin.service.ApartmentFeeValueService;
import org.springframework.stereotype.Service;

/**
 * @author liubo
 *  针对表【apartment_fee_value(公寓&杂费关联表)】的数据库操作Service实现
 *
 */
@Service
public class ApartmentFeeValueServiceImpl extends ServiceImpl<ApartmentFeeValueMapper, ApartmentFeeValue>
        implements ApartmentFeeValueService {

}





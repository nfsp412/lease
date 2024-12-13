package org.asuka.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.asuka.lease.model.entity.PaymentType;
import org.asuka.lease.model.entity.RoomPaymentType;

import java.util.List;

/**
* @author liubo
*  针对表【room_payment_type(房间&支付方式关联表)】的数据库操作Mapper
*
*  com.atguigu.lease.model.RoomPaymentType
*/
public interface RoomPaymentTypeMapper extends BaseMapper<RoomPaymentType> {

    List<PaymentType> getPaymentTypeList(Long id);
}





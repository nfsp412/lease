package org.asuka.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.asuka.lease.model.entity.ApartmentLabel;
import org.asuka.lease.web.admin.mapper.ApartmentLabelMapper;
import org.asuka.lease.web.admin.service.ApartmentLabelService;
import org.springframework.stereotype.Service;

/**
 * @author liubo
 *  针对表【apartment_label(公寓标签关联表)】的数据库操作Service实现
 *
 */
@Service
public class ApartmentLabelServiceImpl extends ServiceImpl<ApartmentLabelMapper, ApartmentLabel>
        implements ApartmentLabelService {

}





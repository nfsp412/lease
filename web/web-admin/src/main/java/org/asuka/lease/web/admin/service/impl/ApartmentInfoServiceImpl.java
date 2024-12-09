package org.asuka.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.asuka.lease.model.entity.ApartmentInfo;
import org.asuka.lease.web.admin.mapper.ApartmentInfoMapper;
import org.asuka.lease.web.admin.service.ApartmentInfoService;
import org.springframework.stereotype.Service;

/**
 * @author liubo
 *  针对表【apartment_info(公寓信息表)】的数据库操作Service实现
 *
 */
@Service
public class ApartmentInfoServiceImpl extends ServiceImpl<ApartmentInfoMapper, ApartmentInfo>
        implements ApartmentInfoService {

}





package org.asuka.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.asuka.lease.model.entity.ApartmentFacility;
import org.asuka.lease.web.admin.mapper.ApartmentFacilityMapper;
import org.asuka.lease.web.admin.service.ApartmentFacilityService;
import org.springframework.stereotype.Service;

/**
 * @author liubo
 * 针对表【apartment_facility(公寓&配套关联表)】的数据库操作Service实现
 *
 */
@Service
public class ApartmentFacilityServiceImpl extends ServiceImpl<ApartmentFacilityMapper, ApartmentFacility>
        implements ApartmentFacilityService {

}





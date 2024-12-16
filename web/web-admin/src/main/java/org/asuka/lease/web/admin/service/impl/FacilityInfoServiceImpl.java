package org.asuka.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.asuka.lease.model.entity.FacilityInfo;
import org.asuka.lease.web.admin.service.FacilityInfoService;
import org.asuka.lease.web.admin.mapper.FacilityInfoMapper;
import org.springframework.stereotype.Service;

/**
 * @author liubo
 *  针对表【facility_info(配套信息表)】的数据库操作Service实现
 *
 */
@Service
public class FacilityInfoServiceImpl extends ServiceImpl<FacilityInfoMapper, FacilityInfo>
        implements FacilityInfoService {

}





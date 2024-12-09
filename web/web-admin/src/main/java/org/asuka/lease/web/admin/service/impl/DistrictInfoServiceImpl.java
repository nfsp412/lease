package org.asuka.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.asuka.lease.model.entity.DistrictInfo;
import org.asuka.lease.web.admin.service.DistrictInfoService;
import org.asuka.lease.web.admin.mapper.DistrictInfoMapper;
import org.springframework.stereotype.Service;

/**
 * @author liubo
 *  针对表【district_info】的数据库操作Service实现
 *
 */
@Service
public class DistrictInfoServiceImpl extends ServiceImpl<DistrictInfoMapper, DistrictInfo>
        implements DistrictInfoService {

}





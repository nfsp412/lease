package org.asuka.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.asuka.lease.model.entity.ProvinceInfo;
import org.asuka.lease.web.admin.service.ProvinceInfoService;
import org.asuka.lease.web.admin.mapper.ProvinceInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author liubo
*  针对表【province_info】的数据库操作Service实现
*
*/
@Service
public class ProvinceInfoServiceImpl extends ServiceImpl<ProvinceInfoMapper, ProvinceInfo>
    implements ProvinceInfoService{

}





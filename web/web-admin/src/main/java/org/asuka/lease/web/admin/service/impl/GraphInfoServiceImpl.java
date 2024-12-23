package org.asuka.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.asuka.lease.model.entity.GraphInfo;
import org.asuka.lease.web.admin.service.GraphInfoService;
import org.asuka.lease.web.admin.mapper.GraphInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author liubo
*  针对表【graph_info(图片信息表)】的数据库操作Service实现
*
*/
@Service
public class GraphInfoServiceImpl extends ServiceImpl<GraphInfoMapper, GraphInfo>
    implements GraphInfoService{

}





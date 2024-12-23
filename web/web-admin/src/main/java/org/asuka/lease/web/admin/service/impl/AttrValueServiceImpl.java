package org.asuka.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.asuka.lease.model.entity.AttrValue;
import org.asuka.lease.web.admin.mapper.AttrValueMapper;
import org.asuka.lease.web.admin.service.AttrValueService;
import org.springframework.stereotype.Service;


/**
* @author liubo
*  针对表【attr_value(房间基本属性值表)】的数据库操作Service实现
*
*/
@Service
public class AttrValueServiceImpl extends ServiceImpl<AttrValueMapper, AttrValue>
    implements AttrValueService {

}





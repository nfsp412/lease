package org.asuka.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.asuka.lease.model.entity.AttrKey;
import org.asuka.lease.web.admin.mapper.AttrKeyMapper;
import org.asuka.lease.web.admin.service.AttrKeyService;
import org.springframework.stereotype.Service;

/**
 * @author liubo
 * @description 针对表【attr_key(房间基本属性表)】的数据库操作Service实现
 * @createDate 2023-07-24 15:48:00
 */
@Service
public class AttrKeyServiceImpl extends ServiceImpl<AttrKeyMapper, AttrKey>
        implements AttrKeyService {

}





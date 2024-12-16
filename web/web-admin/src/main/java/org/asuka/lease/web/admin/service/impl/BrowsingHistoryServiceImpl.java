package org.asuka.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.asuka.lease.model.entity.BrowsingHistory;
import org.asuka.lease.web.admin.mapper.BrowsingHistoryMapper;
import org.asuka.lease.web.admin.service.BrowsingHistoryService;
import org.springframework.stereotype.Service;

/**
* @author liubo
*  针对表【browsing_history(浏览历史)】的数据库操作Service实现
*
*/
@Service
public class BrowsingHistoryServiceImpl extends ServiceImpl<BrowsingHistoryMapper, BrowsingHistory>
    implements BrowsingHistoryService {

}





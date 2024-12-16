package org.asuka.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.asuka.lease.model.entity.LeaseTerm;
import org.asuka.lease.model.entity.RoomLeaseTerm;

import java.util.List;

/**
* @author liubo
*  针对表【room_lease_term(房间租期管理表)】的数据库操作Mapper
*
*  com.atguigu.lease.model.RoomLeaseTerm
*/
public interface RoomLeaseTermMapper extends BaseMapper<RoomLeaseTerm> {

    List<LeaseTerm> getLeaseTermList(Long id);
}





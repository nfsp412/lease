package org.asuka.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.asuka.lease.model.entity.FacilityInfo;
import org.asuka.lease.model.entity.RoomFacility;

import java.util.List;

/**
* @author liubo
*  针对表【room_facility(房间&配套关联表)】的数据库操作Mapper
*
*  com.atguigu.lease.model.RoomFacility
*/
public interface RoomFacilityMapper extends BaseMapper<RoomFacility> {

    List<FacilityInfo> getFacilityInfoList(Long id);
}





package org.asuka.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.asuka.lease.model.entity.RoomInfo;
import org.asuka.lease.web.admin.vo.room.RoomItemVo;
import org.asuka.lease.web.admin.vo.room.RoomQueryVo;

/**
* @author liubo
*  针对表【room_info(房间信息表)】的数据库操作Mapper
*
*  com.atguigu.lease.model.RoomInfo
*/
public interface RoomInfoMapper extends BaseMapper<RoomInfo> {

    IPage<RoomItemVo> getRoomItemVoList(Page<RoomItemVo> page, RoomQueryVo queryVo);
}





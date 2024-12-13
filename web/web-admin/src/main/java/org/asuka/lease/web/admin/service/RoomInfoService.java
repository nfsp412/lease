package org.asuka.lease.web.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.asuka.lease.model.entity.RoomInfo;
import org.asuka.lease.web.admin.vo.room.RoomDetailVo;
import org.asuka.lease.web.admin.vo.room.RoomItemVo;
import org.asuka.lease.web.admin.vo.room.RoomQueryVo;
import org.asuka.lease.web.admin.vo.room.RoomSubmitVo;

import java.util.List;

/**
* @author liubo
*  针对表【room_info(房间信息表)】的数据库操作Service
*
*/
public interface RoomInfoService extends IService<RoomInfo> {

    IPage<RoomItemVo> getRoomItemVoList(Page<RoomItemVo> page, RoomQueryVo queryVo);

    void removeRoomInfoById(Long id);

    void saveOrUpdateRoomSubmitVo(RoomSubmitVo roomSubmitVo);

    RoomDetailVo getRoomDetailVo(Long id);

    List<RoomInfo> listFreeRoomInfo(Long id);
}

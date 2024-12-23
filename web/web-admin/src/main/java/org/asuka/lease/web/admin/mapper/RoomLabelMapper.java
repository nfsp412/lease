package org.asuka.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.asuka.lease.model.entity.LabelInfo;
import org.asuka.lease.model.entity.RoomLabel;

import java.util.List;

/**
* @author liubo
*  针对表【room_label(房间&标签关联表)】的数据库操作Mapper
*
*  com.atguigu.lease.model.RoomLabel
*/
public interface RoomLabelMapper extends BaseMapper<RoomLabel> {

    List<LabelInfo> getLabelInfoList(Long id);
}





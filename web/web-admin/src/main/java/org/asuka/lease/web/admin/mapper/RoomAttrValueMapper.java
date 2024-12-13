package org.asuka.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.asuka.lease.model.entity.RoomAttrValue;
import org.asuka.lease.web.admin.vo.attr.AttrValueVo;

import java.util.List;

/**
* @author liubo
*  针对表【room_attr_value(房间&基本属性值关联表)】的数据库操作Mapper
*
*  com.atguigu.lease.model.RoomAttrValue
*/
public interface RoomAttrValueMapper extends BaseMapper<RoomAttrValue> {

    List<AttrValueVo> getAttrValueVoList(Long id);
}





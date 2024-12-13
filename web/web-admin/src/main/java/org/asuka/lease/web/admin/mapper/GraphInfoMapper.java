package org.asuka.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.asuka.lease.model.entity.GraphInfo;
import org.asuka.lease.model.enums.ItemType;
import org.asuka.lease.web.admin.vo.graph.GraphVo;

import java.util.List;


/**
* @author liubo
*  针对表【graph_info(图片信息表)】的数据库操作Mapper
*
*  com.atguigu.lease.model.GraphInfo
*/
public interface GraphInfoMapper extends BaseMapper<GraphInfo> {

    List<GraphVo> getGraphVoList(ItemType itemType, Long id);
}





package org.asuka.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.asuka.lease.model.entity.LabelInfo;

import java.util.List;


/**
* @author liubo
*  针对表【label_info(标签信息表)】的数据库操作Mapper
*
*  com.atguigu.lease.model.LabelInfo
*/
public interface LabelInfoMapper extends BaseMapper<LabelInfo> {

    List<LabelInfo> getLabelInfoList(Long id);
}





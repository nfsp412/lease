package org.asuka.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.asuka.lease.model.entity.AttrKey;
import org.asuka.lease.web.admin.vo.attr.AttrKeyVo;

import java.util.List;


/**
* @author liubo
*  针对表【attr_key(房间基本属性表)】的数据库操作Mapper
*
*  com.atguigu.lease.model.AttrKey
*/
public interface AttrKeyMapper extends BaseMapper<AttrKey> {

    List<AttrKeyVo> getAttrKeyVoList();
}





package org.asuka.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.asuka.lease.model.entity.FeeKey;
import org.asuka.lease.web.admin.vo.fee.FeeKeyVo;

import java.util.List;

/**
* @author liubo
*  针对表【fee_key(杂项费用名称表)】的数据库操作Mapper
*
*  com.atguigu.lease.model.FeeKey
*/
public interface FeeKeyMapper extends BaseMapper<FeeKey> {

    List<FeeKeyVo> getFeeKeyVoList();
}





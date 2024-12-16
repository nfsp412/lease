package org.asuka.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.asuka.lease.model.entity.FeeValue;
import org.asuka.lease.web.admin.vo.fee.FeeValueVo;

import java.util.List;


/**
* @author liubo
*  针对表【fee_value(杂项费用值表)】的数据库操作Mapper
*
*  com.atguigu.lease.model.FeeValue
*/
public interface FeeValueMapper extends BaseMapper<FeeValue> {

    List<FeeValueVo> getFeeValueVoList(Long id);
}





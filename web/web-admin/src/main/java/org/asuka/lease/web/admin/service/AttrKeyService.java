package org.asuka.lease.web.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.asuka.lease.model.entity.AttrKey;
import org.asuka.lease.web.admin.vo.attr.AttrKeyVo;

import java.util.List;


/**
* @author liubo
*  针对表【attr_key(房间基本属性表)】的数据库操作Service
*
*/
public interface AttrKeyService extends IService<AttrKey> {

    List<AttrKeyVo> getAttrKeyVoList();

    void deleteAttrKeyAndValue(Long attrKeyId);
}

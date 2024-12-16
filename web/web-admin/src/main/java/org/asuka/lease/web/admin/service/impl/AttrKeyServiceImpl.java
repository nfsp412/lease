package org.asuka.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.asuka.lease.model.entity.AttrKey;
import org.asuka.lease.model.entity.AttrValue;
import org.asuka.lease.web.admin.mapper.AttrKeyMapper;
import org.asuka.lease.web.admin.service.AttrKeyService;
import org.asuka.lease.web.admin.service.AttrValueService;
import org.asuka.lease.web.admin.vo.attr.AttrKeyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author liubo
 * 针对表【attr_key(房间基本属性表)】的数据库操作Service实现
 */
@Service
public class AttrKeyServiceImpl extends ServiceImpl<AttrKeyMapper, AttrKey>
        implements AttrKeyService {
    @Autowired
    private AttrValueService attrValueService;

    @Transactional()
    @Override
    public void deleteAttrKeyAndValue(Long attrKeyId) {
        //删除key
        this.removeById(attrKeyId);
//        System.out.println(1 / 0); //测试事务
        //删除value
        LambdaQueryWrapper<AttrValue> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AttrValue::getAttrKeyId, attrKeyId);
        attrValueService.remove(wrapper);
    }

    @Resource
    private AttrKeyMapper attrKeyMapper;

    @Override
    public List<AttrKeyVo> getAttrKeyVoList() {
        return attrKeyMapper.getAttrKeyVoList();
    }
}





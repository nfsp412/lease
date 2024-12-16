package org.asuka.lease.web.admin.controller.apartment;


import org.asuka.lease.common.result.Result;
import org.asuka.lease.model.entity.AttrKey;
import org.asuka.lease.model.entity.AttrValue;
import org.asuka.lease.web.admin.service.AttrKeyService;
import org.asuka.lease.web.admin.service.AttrValueService;
import org.asuka.lease.web.admin.vo.attr.AttrKeyVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "房间属性管理")
@RestController
@RequestMapping("/admin/attr")
public class AttrController {
    @Autowired
    private AttrValueService attrValueService;

    @Autowired
    private AttrKeyService attrKeyService;

    @Operation(summary = "新增或更新属性名称")
    @PostMapping("key/saveOrUpdate")
    public Result saveOrUpdateAttrKey(@RequestBody AttrKey attrKey) {
        attrKeyService.saveOrUpdate(attrKey);
        //有默认的异常处理规则?在哪里
//        if (flag) {
//            return Result.ok();
//        } else {
//            return Result.fail();
//        }
        return Result.ok();
    }

    @Operation(summary = "新增或更新属性值")
    @PostMapping("value/saveOrUpdate")
    public Result saveOrUpdateAttrValue(@RequestBody AttrValue attrValue) {
        attrValueService.saveOrUpdate(attrValue);
        return Result.ok();
    }


    @Operation(summary = "查询全部属性名称和属性值列表")
    @GetMapping("list")
    public Result<List<AttrKeyVo>> listAttrInfo() {
        List<AttrKeyVo> lists = attrKeyService.getAttrKeyVoList();
        return Result.ok(lists);
    }

    @Operation(summary = "根据id删除属性名称")
    @DeleteMapping("key/deleteById")
    public Result removeAttrKeyById(@RequestParam Long attrKeyId) {
        attrKeyService.deleteAttrKeyAndValue(attrKeyId);
        return Result.ok();
    }

    @Operation(summary = "根据id删除属性值")
    @DeleteMapping("value/deleteById")
    public Result removeAttrValueById(@RequestParam Long id) {
        attrValueService.removeById(id);
        return Result.ok();
    }

}

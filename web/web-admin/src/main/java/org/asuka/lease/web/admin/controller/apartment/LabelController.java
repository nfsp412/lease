package org.asuka.lease.web.admin.controller.apartment;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.asuka.lease.common.result.Result;
import org.asuka.lease.model.entity.LabelInfo;
import org.asuka.lease.model.enums.ItemType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.asuka.lease.web.admin.service.LabelInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "标签管理")
@RestController
@RequestMapping("/admin/label")
public class LabelController {
    @Autowired
    private LabelInfoService labelInfoService;

    /**
     * Failed to convert value of type 'java.lang.String' to required type 'org.asuka.lease.model.enums.ItemType';
     *
     * @param type
     * @return
     */
    @Operation(summary = "（根据类型）查询标签列表")
    @GetMapping("list")
    public Result<List<LabelInfo>> labelList(@RequestParam(required = false) ItemType type) {
        LambdaQueryWrapper<LabelInfo> wrapper = new LambdaQueryWrapper<>();
//        if (ItemType.APARTMENT.equals(type)){
//            //apartment
//            wrapper.eq(LabelInfo::getType,"2");
//        }else if (ItemType.ROOM.equals(type)){
//            //room
//            wrapper.eq(LabelInfo::getType,"1");
//        }
        wrapper.eq(type != null, LabelInfo::getType, type);//第一个判断条件是为了实现动态sql功能,即如果是true才拼接后面的条件
        List<LabelInfo> list = labelInfoService.list(wrapper);
        return Result.ok(list);
    }

    @Operation(summary = "新增或修改标签信息")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdateLabel(@RequestBody LabelInfo labelInfo) {
        labelInfoService.saveOrUpdate(labelInfo);
        return Result.ok();
    }

    @Operation(summary = "根据id删除标签信息")
    @DeleteMapping("deleteById")
    public Result deleteLabelById(@RequestParam Long id) {
        labelInfoService.removeById(id);
        return Result.ok();
    }
}

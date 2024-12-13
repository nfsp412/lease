package org.asuka.lease.web.admin.controller.apartment;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.asuka.lease.common.result.Result;
import org.asuka.lease.model.entity.FacilityInfo;
import org.asuka.lease.model.enums.ItemType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.asuka.lease.web.admin.service.FacilityInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "配套管理")
@RestController
@RequestMapping("/admin/facility")
public class FacilityController {
    @Autowired
    private FacilityInfoService facilityInfoService;

    @Operation(summary = "[根据类型]查询配套信息列表")
    @GetMapping("list")
    public Result<List<FacilityInfo>> listFacility(@RequestParam(required = false) ItemType type) {
        LambdaQueryWrapper<FacilityInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(type != null, FacilityInfo::getType, type);
        List<FacilityInfo> list = facilityInfoService.list(wrapper);
        return Result.ok(list);
    }

    @Operation(summary = "新增或修改配套信息")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody FacilityInfo facilityInfo) {
        facilityInfoService.saveOrUpdate(facilityInfo);
        return Result.ok();
    }

    @Operation(summary = "根据id删除配套信息")
    @DeleteMapping("deleteById")
    public Result removeFacilityById(@RequestParam Long id) {
        facilityInfoService.removeById(id);
        return Result.ok();
    }

}

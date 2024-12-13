package org.asuka.lease.web.admin.controller.apartment;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.asuka.lease.common.result.Result;
import org.asuka.lease.model.entity.RoomInfo;
import org.asuka.lease.model.enums.ReleaseStatus;
import org.asuka.lease.web.admin.service.RoomInfoService;
import org.asuka.lease.web.admin.vo.room.RoomDetailVo;
import org.asuka.lease.web.admin.vo.room.RoomItemVo;
import org.asuka.lease.web.admin.vo.room.RoomQueryVo;
import org.asuka.lease.web.admin.vo.room.RoomSubmitVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "房间信息管理")
@RestController
@RequestMapping("/admin/room")
public class RoomController {
    @Autowired
    private RoomInfoService roomInfoService;

    @Operation(summary = "保存或更新房间信息")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody RoomSubmitVo roomSubmitVo) {
        roomInfoService.saveOrUpdateRoomSubmitVo(roomSubmitVo);
        return Result.ok();
    }

    @Operation(summary = "根据条件分页查询房间列表")
    @GetMapping("pageItem")
    public Result<IPage<RoomItemVo>> pageItem(@RequestParam long current,
                                              @RequestParam long size,
                                              RoomQueryVo queryVo) {
        Page<RoomItemVo> page = new Page<>(current, size);
        IPage<RoomItemVo> roomItemVoIPage = roomInfoService.getRoomItemVoList(page, queryVo);
        return Result.ok(roomItemVoIPage);
    }

    @Operation(summary = "根据id获取房间详细信息")
    @GetMapping("getDetailById")
    public Result<RoomDetailVo> getDetailById(@RequestParam Long id) {
        RoomDetailVo roomDetailVo = roomInfoService.getRoomDetailVo(id);
        return Result.ok(roomDetailVo);
    }

    @Operation(summary = "根据id删除房间信息")
    @DeleteMapping("removeById")
    public Result removeById(@RequestParam Long id) {
        roomInfoService.removeRoomInfoById(id);
        return Result.ok();
    }

    @Operation(summary = "根据id修改房间发布状态")
    @PostMapping("updateReleaseStatusById")
    public Result updateReleaseStatusById(Long id,
                                          ReleaseStatus status) {
        LambdaUpdateWrapper<RoomInfo> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(RoomInfo::getId, id);
        wrapper.set(RoomInfo::getIsRelease, status);
        roomInfoService.update(wrapper);
        return Result.ok();
    }

    @GetMapping("listBasicByApartmentId")
    @Operation(summary = "根据公寓id查询房间列表")
    public Result<List<RoomInfo>> listBasicByApartmentId(Long id) {
        //这样写会把能租的或者不能租的都查询出来,但是我们应该只能选择能租的
//        LambdaQueryWrapper<RoomInfo> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(RoomInfo::getApartmentId, id);
//        wrapper.eq(RoomInfo::getIsRelease, ReleaseStatus.RELEASED);
//        List<RoomInfo> list = roomInfoService.list(wrapper);
        List<RoomInfo> list = roomInfoService.listFreeRoomInfo(id);
        return Result.ok(list);
    }

}

package org.asuka.lease.web.admin.controller.system;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.minio.Digest;
import org.apache.commons.codec.digest.DigestUtils;
import org.asuka.lease.common.result.Result;
import org.asuka.lease.model.entity.SystemUser;
import org.asuka.lease.model.enums.BaseStatus;
import org.asuka.lease.web.admin.service.SystemUserService;
import org.asuka.lease.web.admin.vo.system.user.SystemUserItemVo;
import org.asuka.lease.web.admin.vo.system.user.SystemUserQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "后台用户信息管理")
@RestController
@RequestMapping("/admin/system/user")
public class SystemUserController {
    @Autowired
    private SystemUserService systemUserService;

    @Operation(summary = "根据条件分页查询后台用户列表")
    @GetMapping("page")
    public Result<IPage<SystemUserItemVo>> page(@RequestParam long current,
                                                @RequestParam long size,
                                                SystemUserQueryVo queryVo) {
        Page<SystemUserItemVo> page = new Page<>(current, size);
        IPage<SystemUserItemVo> systemUserItemVoIPage = systemUserService.getSystemUserItemVoPage(page, queryVo);
        return Result.ok(systemUserItemVoIPage);
    }

    @Operation(summary = "根据ID查询后台用户信息")
    @GetMapping("getById")
    public Result<SystemUserItemVo> getById(@RequestParam Long id) {
        SystemUserItemVo systemUserItemVo = systemUserService.getSystemUserItemVoById(id);
        return Result.ok(systemUserItemVo);
    }

    @Operation(summary = "保存或更新后台用户信息")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody SystemUser systemUser) {
        //密码加密处理
        if (systemUser.getPassword() != null) {
            systemUser.setPassword(DigestUtils.md5Hex(systemUser.getPassword()));
        }
        systemUserService.saveOrUpdate(systemUser);
        return Result.ok();
    }

    @Operation(summary = "判断后台用户名是否可用")
    @GetMapping("isUserNameAvailable")
    public Result<Boolean> isUsernameExists(@RequestParam String username) {
        LambdaQueryWrapper<SystemUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SystemUser::getUsername, username);
        List<SystemUser> list = systemUserService.list(wrapper);
        if (list.isEmpty()) {
            return Result.ok(true);
        } else
            return Result.ok(false);
    }

    @DeleteMapping("deleteById")
    @Operation(summary = "根据ID删除后台用户信息")
    public Result removeById(@RequestParam Long id) {
        systemUserService.deleteSystemUserById(id);
        return Result.ok();
    }

    @Operation(summary = "根据ID修改后台用户状态")
    @PostMapping("updateStatusByUserId")
    public Result updateStatusByUserId(@RequestParam Long id,
                                       @RequestParam BaseStatus status) {
        LambdaUpdateWrapper<SystemUser> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(SystemUser::getId, id);
        wrapper.set(SystemUser::getStatus, status);
        systemUserService.update(wrapper);
        return Result.ok();
    }
}

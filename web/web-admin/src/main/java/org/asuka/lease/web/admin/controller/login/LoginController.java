package org.asuka.lease.web.admin.controller.login;


import io.jsonwebtoken.Claims;
import org.asuka.lease.common.login.LoginUser;
import org.asuka.lease.common.login.LoginUserHolder;
import org.asuka.lease.common.result.Result;
import org.asuka.lease.common.utils.JwtUtil;
import org.asuka.lease.web.admin.service.LoginService;
import org.asuka.lease.web.admin.service.SystemUserService;
import org.asuka.lease.web.admin.vo.login.CaptchaVo;
import org.asuka.lease.web.admin.vo.login.LoginVo;
import org.asuka.lease.web.admin.vo.system.user.SystemUserInfoVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "后台管理系统登录管理")
@RestController
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private SystemUserService systemUserService;

    @Operation(summary = "获取图形验证码")
    @GetMapping("login/captcha")
    public Result<CaptchaVo> getCaptcha() {
        //获取图形验证码 CaptchaVo
        CaptchaVo captchaVo = loginService.getCaptchaVo();
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, HEAD");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Headers", "access-control-allow-origin, authority, content-type, version-info, X-Requested-With");
        return Result.ok(captchaVo);
    }

    @Operation(summary = "登录")
    @PostMapping("login")
    public Result<String> login(@RequestBody LoginVo loginVo) {
        String token = loginService.login(loginVo);
        return Result.ok(token);
    }

    @Operation(summary = "获取登陆用户个人信息")
    @GetMapping("info")
    public Result<SystemUserInfoVo> info(/*@RequestHeader("access-token") String token*/) {
//        Claims claims = JwtUtil.parseToken(token);
//        Long userId = claims.get("userId", Long.class);
        LoginUser loginUser = LoginUserHolder.getLoginUser();
        Long userId = loginUser.getUserId();
        SystemUserInfoVo systemUserInfoVo = systemUserService.getSystemUserInfoVoById(userId);
        return Result.ok(systemUserInfoVo);
    }
}

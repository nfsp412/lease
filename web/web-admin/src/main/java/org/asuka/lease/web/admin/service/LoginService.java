package org.asuka.lease.web.admin.service;


import org.asuka.lease.web.admin.vo.login.CaptchaVo;
import org.asuka.lease.web.admin.vo.login.LoginVo;

public interface LoginService {

    CaptchaVo getCaptchaVo();

    String login(LoginVo loginVo);
}

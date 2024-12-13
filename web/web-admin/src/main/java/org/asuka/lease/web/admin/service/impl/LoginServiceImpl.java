package org.asuka.lease.web.admin.service.impl;

import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import org.asuka.lease.common.constant.RedisConstant;
import org.asuka.lease.model.entity.SystemUser;
import org.asuka.lease.web.admin.mapper.LoginMapper;
import org.asuka.lease.web.admin.service.LoginService;
import org.asuka.lease.web.admin.vo.login.CaptchaVo;
import org.asuka.lease.web.admin.vo.login.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private LoginMapper loginMapper;

    /**
     * 前端请求图片验证码时,后端接口有两个操作
     * 1.随机拼接一个字符串,作为key,将图片验证码的code作为value,存入redis
     * 并设置验证码过期时间(也就是redis的key的ttl)
     * 2.将图片验证码的图片image和redis的key返回
     * 到这里接口功能结束
     *
     * @return CaptchaVo
     */
    @Override
    public CaptchaVo getCaptchaVo() {
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 4);
        specCaptcha.setCharType(Captcha.TYPE_DEFAULT);
        //获取验证码里面的code
        String code = specCaptcha.text().toLowerCase();
        //获取验证码图片
        String image = specCaptcha.toBase64();
        //拼接redis的key admin:login:UUID
        String key = RedisConstant.ADMIN_LOGIN_PREFIX + UUID.randomUUID();
        System.out.println("redis key is " + key);
        //存入redis
        stringRedisTemplate.opsForValue()
                .set(key, code, RedisConstant.ADMIN_LOGIN_CAPTCHA_TTL_SEC, TimeUnit.SECONDS);
        //同时将redis的key和图片响应前端
        return new CaptchaVo(image, key);
    }

    @Override
    public String login(LoginVo loginVo) {
        //根据前端提供的key,去redis查询value,该value就是验证码code
        String key = loginVo.getCaptchaKey();
        String code = stringRedisTemplate.opsForValue().get(key);
        if (!Objects.equals(code, loginVo.getCaptchaCode())) {
            return null;
        }
        //和前端提供的code进行匹配
        //username和password的匹配
        SystemUser systemUser = loginMapper.getSystemUserByUsername(loginVo.getUsername());
        //全部匹配成功,生成一个token返回给前端
        return null;
    }
}

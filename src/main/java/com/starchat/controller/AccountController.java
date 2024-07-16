package com.starchat.controller;

import com.google.common.collect.ImmutableMap;
import com.starchat.common.constants.Constants;
import com.starchat.common.enums.ErrorCodeEnum;
import com.starchat.entity.dto.TokenUserDto;
import com.starchat.entity.vo.ResVO;
import com.starchat.exception.BusinessException;
import com.starchat.service.UserService;
import com.starchat.service.redis.RedisService;
import com.wf.captcha.ArithmeticCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

/**
 * Account Controller
 *
 * @author Jack
 */
@RestController
@RequestMapping("/account")
public class AccountController extends BaseController {

    private final UserService userService;
    private final RedisService redisService;

    @Autowired
    public AccountController(UserService userService, RedisService redisService) {
        this.userService = userService;
        this.redisService = redisService;
    }

    @RequestMapping("/checkCode")
    public ResVO checkCode() {
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(100, 40);
        String code = captcha.text();
        String checkCodeBase64 = captcha.toBase64();

        String checkCodeKey = UUID.randomUUID().toString();
        String redisKey = Constants.Redis.KEY_CHECK_CODE + checkCodeKey;
        long expireTime = Constants.Redis.EXPIRE_TIME_1_MINUTE;
        redisService.setWithExpire(redisKey, code, expireTime);

        Map<String, Object> result = ImmutableMap.of(
                Constants.Captcha.CHECK_CODE, checkCodeBase64,
                Constants.Captcha.CHECK_CODE_KEY, checkCodeKey
        );
        return success(result);
    }

    @RequestMapping(value = "/register")
    public ResVO register(String checkCodeKey,
                          String email,
                          String password,
                          String nickname,
                          String checkCode) {
        try {
            if (!checkCode.equalsIgnoreCase(redisService.get(Constants.Redis.KEY_CHECK_CODE + checkCodeKey))) {
                throw new BusinessException(ErrorCodeEnum.ERROR_A0001);
            }
            userService.register(email, nickname, password);
            return success();
        } finally {
            redisService.delete(Constants.Redis.KEY_CHECK_CODE + checkCodeKey);
        }
    }

    @RequestMapping(value = "/login")
    public ResVO login(String checkCodeKey,
                          String email,
                          String password,
                          String checkCode) {
        try {
            if (!checkCode.equalsIgnoreCase(redisService.get(Constants.Redis.KEY_CHECK_CODE + checkCodeKey))) {
                throw new BusinessException(ErrorCodeEnum.ERROR_A0001);
            }
            TokenUserDto tokenUserDto = userService.login(email, password);
            return success(tokenUserDto);
        } finally {
            redisService.delete(Constants.Redis.KEY_CHECK_CODE + checkCodeKey);
        }
    }
}

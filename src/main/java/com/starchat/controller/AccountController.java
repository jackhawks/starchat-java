package com.starchat.controller;

import com.google.common.collect.ImmutableMap;
import com.starchat.common.constants.Constants;
import com.starchat.service.redis.RedisService;
import com.starchat.entity.vo.ResVO;
import com.wf.captcha.ArithmeticCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Account Controller
 *
 * @author Jack
 */
@Validated
@RestController
@RequestMapping("account")
public class AccountController extends BaseController {

    private final RedisService redisService;

    @Autowired
    public AccountController(RedisService redisService) {
        this.redisService = redisService;
    }

    @RequestMapping("checkCode")
    public ResVO<Map<String, String>> checkCode() {
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(100, 40);
        String code = captcha.text();
        String checkCodeKey = UUID.randomUUID().toString();
        String redisKey = Constants.Redis.KEY_CHECK_CODE + checkCodeKey;
        long expireTime = Constants.Redis.EXPIRE_TIME_1_MINUTE;
        redisService.setWithExpire(redisKey, code, expireTime);
        String checkCodeBase64 = captcha.toBase64();
        Map<String, String> result = ImmutableMap.of(
                Constants.Captcha.CHECK_CODE, checkCodeBase64,
                Constants.Captcha.CHECK_CODE_KEY, checkCodeKey
        );
        return success(result);
    }
}

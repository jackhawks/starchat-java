package com.starchat.controller;

import com.starchat.common.constants.Constants;
import com.starchat.entity.vo.ResVO;
import com.starchat.utils.RedisUtil;
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

    private final RedisUtil redisUtil;

    @Autowired
    public AccountController(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @RequestMapping("checkCode")
    public ResVO<Map<String, String>> checkCode() {
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(100, 40);
        String code = captcha.text();
        String checkCodeKey = UUID.randomUUID().toString();
        redisUtil.setWithExpire(Constants.Redis.KEY_CHECK_CODE + checkCodeKey, code,
                Constants.Redis.EXPIRE_TIME_1_MINUTE);
        String checkCodeBase64 = captcha.toBase64();
        Map<String, String> result = new HashMap<>();
        result.put("checkCode", checkCodeBase64);
        result.put("checkCodeKey", checkCodeKey);
        return success(result);
    }
}

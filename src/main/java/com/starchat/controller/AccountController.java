package com.starchat.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.ImmutableMap;
import com.starchat.common.constants.Constants;
import com.starchat.common.enums.ErrorCodeEnum;
import com.starchat.entity.User;
import com.starchat.entity.dto.TokenUserDto;
import com.starchat.entity.mapping.UserMapper;
import com.starchat.entity.vo.ResVO;
import com.starchat.entity.vo.UserVO;
import com.starchat.exception.BusinessException;
import com.starchat.service.UserService;
import com.starchat.service.redis.RedisUtil;
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
    private final RedisUtil redisUtil;

    @Autowired
    public AccountController(UserService userService, RedisUtil redisUtil1) {
        this.userService = userService;
        this.redisUtil = redisUtil1;
    }

    @RequestMapping("/checkCode")
    public ResVO checkCode() {
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(100, 40);
        String code = captcha.text();
        String checkCodeBase64 = captcha.toBase64();

        String checkCodeKey = UUID.randomUUID().toString();
        String redisKey = Constants.Redis.KEY_CHECK_CODE + checkCodeKey;
        long expireTime = Constants.Redis.EXPIRE_TIME_1_HOUR;
        redisUtil.set(redisKey, code, expireTime);

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
            String checkCodeValue = redisUtil.get(Constants.Redis.KEY_CHECK_CODE + checkCodeKey, String.class);
            if (!checkCode.equalsIgnoreCase(checkCodeValue)) {
                throw new BusinessException(ErrorCodeEnum.ERROR_A0001);
            }
            userService.register(email, nickname, password);
            return success();
        } finally {
            redisUtil.delete(Constants.Redis.KEY_CHECK_CODE + checkCodeKey);
        }
    }

    @RequestMapping(value = "/login")
    public ResVO login(String checkCodeKey,
                       String email,
                       String password,
                       String checkCode) {
        try {
            String checkCodeValue = redisUtil.get(Constants.Redis.KEY_CHECK_CODE + checkCodeKey, String.class);
            if (!checkCode.equalsIgnoreCase(checkCodeValue)) {
                throw new BusinessException(ErrorCodeEnum.ERROR_A0001);
            }
            TokenUserDto tokenUserDto = userService.login(email, password);

            User user = userService.getOne(new LambdaQueryWrapper<User>()
                    .eq(User::getUserId, tokenUserDto.getUserId()));

            UserVO userVO = UserMapper.INSTANCE.userToUserVO(user);
            userVO.setToken(tokenUserDto.getToken());
            userVO.setAdmin(tokenUserDto.getAdmin());
            return success(userVO);
        } finally {
            redisUtil.delete(Constants.Redis.KEY_CHECK_CODE + checkCodeKey);
        }
    }
}

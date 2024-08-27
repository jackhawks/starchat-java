package com.starchat.service.redis;

import com.starchat.common.constants.Constants;
import com.starchat.entity.dto.TokenUserDto;
import org.springframework.stereotype.Component;

@Component
public class RedisService {

    private final RedisUtil redisUtil;

    public RedisService(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    public Long getUserHeartbeat(String userId) {
        return redisUtil.get(Constants.Redis.KEY_WS_USER_HEARTBEAT + userId, Long.class);
    }

    public void saveTokenUserDto(TokenUserDto tokenUserDto) {
        redisUtil.set(Constants.Redis.KEY_WS_TOKEN + tokenUserDto.getToken(), tokenUserDto, Constants.Redis.EXPIRE_TIME_2_DAYS);
        redisUtil.set(Constants.Redis.KEY_WS_TOKEN_USERID + tokenUserDto.getUserId(), tokenUserDto.getToken(), Constants.Redis.EXPIRE_TIME_2_DAYS);
    }
}

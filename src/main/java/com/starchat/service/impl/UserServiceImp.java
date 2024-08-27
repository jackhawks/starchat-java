package com.starchat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starchat.common.constants.Constants;
import com.starchat.common.enums.ErrorCodeEnum;
import com.starchat.common.enums.JoinTypeEnum;
import com.starchat.common.enums.UserStatusEnum;
import com.starchat.config.MyConfigProperties;
import com.starchat.entity.User;
import com.starchat.entity.dto.TokenUserDto;
import com.starchat.exception.BusinessException;
import com.starchat.mapper.UserMapper;
import com.starchat.service.UserService;
import com.starchat.service.redis.RedisService;
import com.starchat.service.redis.RedisUtil;
import com.starchat.utils.IdUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * User Service Impl
 *
 * @author Jack
 */
@Service
public class UserServiceImp extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;
    private final MyConfigProperties myConfigProperties;
    private final RedisService redisService;

    public UserServiceImp(UserMapper userMapper, MyConfigProperties myConfigProperties, RedisService redisService) {
        this.userMapper = userMapper;
        this.myConfigProperties = myConfigProperties;
        this.redisService = redisService;
    }

    @Override
    public void register(String email, String nickname, String password) {
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getEmail, email));
        if (user != null) {
            throw new BusinessException(ErrorCodeEnum.ERROR_A0001);
        }

        String userId = IdUtil.generateUserId();

        User newUser = new User()
                .setUserId(userId)
                .setEmail(email)
                .setNickname(nickname)
                .setPassword(DigestUtils.md5Hex(password))
                .setUserStatus(UserStatusEnum.ACTIVE.getStatus())
                .setJoinType(JoinTypeEnum.APPLY.getType())
                .setCreateTime(LocalDateTime.now())
                .setLastOfflineTime(LocalDateTime.now());

        userMapper.insert(newUser);
    }

    @Override
    public TokenUserDto login(String email, String password) {
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getEmail, email));
        if (user == null) {
            throw new BusinessException(ErrorCodeEnum.ERROR_A0001);
        }
        if (UserStatusEnum.BANNED.getStatus().equals(user.getUserStatus())) {
            throw new BusinessException(ErrorCodeEnum.ERROR_A0001);
        }
        // TODO 查询我的群组
        // TODO 查询我的联系人
        TokenUserDto tokenUserInfoDto = getTokenUserInfoDto(user);

        Long lastHeartbeat = redisService.getUserHeartbeat(user.getUserId());
        if (lastHeartbeat != null) {
            throw new BusinessException("此账号已经被登陆, 请先退出其他登陆");
        }

        String token = DigestUtils.md5Hex(tokenUserInfoDto.getUserId()
                + RandomStringUtils.randomAlphanumeric(Constants.Length._20));

        tokenUserInfoDto.setToken(token);

        redisService.saveTokenUserDto(tokenUserInfoDto);


        return tokenUserInfoDto;
    }

    private TokenUserDto getTokenUserInfoDto(User user) {
        TokenUserDto tokenUserDto = new TokenUserDto();
        tokenUserDto.setUserId(user.getUserId());
        tokenUserDto.setNickname(user.getNickname());
        List<String> emails = myConfigProperties.getAdmin().getEmails();
        tokenUserDto.setAdmin(!emails.isEmpty() && emails.contains(user.getEmail()));
        return tokenUserDto;
    }
}

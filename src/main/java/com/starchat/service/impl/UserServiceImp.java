package com.starchat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.starchat.common.enums.ErrorCodeEnum;
import com.starchat.common.enums.UserStatusEnum;
import com.starchat.config.MyConfigProperties;
import com.starchat.entity.User;
import com.starchat.entity.dto.TokenUserDto;
import com.starchat.exception.BusinessException;
import com.starchat.mapper.UserMapper;
import com.starchat.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starchat.utils.IdUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
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


    public UserServiceImp(UserMapper userMapper, MyConfigProperties myConfigProperties) {
        this.userMapper = userMapper;
        this.myConfigProperties = myConfigProperties;
    }

    @Override
    public void register(String email, String nickname, String password) {
        User user = userMapper.selectByEmail(email);
        if (user.isNotNull()) {
            throw new BusinessException(ErrorCodeEnum.ERROR_A0001);
        }

        String userId = IdUtil.generateUserId();

        User newUser = new User()
                .setUserId(userId)
                .setEmail(email)
                .setNickname(nickname)
                .setPassword(DigestUtils.md5Hex(password))
                .setUserStatus(UserStatusEnum.ACTIVE.getStatus())
                .setCreateTime(LocalDateTime.now())
                .setLastOfflineTime(LocalDateTime.now());

        userMapper.insert(newUser);
    }

    @Override
    public TokenUserDto login(String email, String password) {
        User user = userMapper.selectByEmail(email);
        if (user.isNotNull() || !user.getPassword().equals(DigestUtils.md5Hex(password))) {
            throw new BusinessException(ErrorCodeEnum.ERROR_A0001);
        }
        if (UserStatusEnum.BANNED.getStatus().equals(user.getUserStatus())) {
            throw new BusinessException(ErrorCodeEnum.ERROR_A0001);
        }
        TokenUserDto tokenUserInfoDto = getTokenUserInfoDto(user);

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

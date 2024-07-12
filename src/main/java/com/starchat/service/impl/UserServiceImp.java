package com.starchat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.starchat.common.enums.UserStatusEnum;
import com.starchat.entity.User;
import com.starchat.exception.BusinessException;
import com.starchat.mapper.UserMapper;
import com.starchat.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starchat.utils.IdUtil;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * User Service Impl
 *
 * @author Jack
 */
@Service
public class UserServiceImp extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;

    public UserServiceImp(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void register(String email, String nickname, String password) {
        User user = userMapper.selectByEmail(email);
        if (null != user) {
            throw new BusinessException("邮箱账号已经存在");
        }

        Date curDate = new Date();
        String userId = IdUtil.generateUserId();

//        User newUser = new User()
//                .setUserId(userId)
//                .setNickname(nickname)
//                .setEmail(email)
//                .setPassword(password)
//                .setUserStatus(UserStatusEnum.ACTIVE.getCode())
//                .setCreateTime()
//                .setLastOfflineTime(curDate);
//
//        userMapper.insert(newUser);
//
//        //更新靓号状态
//        if (useBeautyAccount) {
//            UserInfoBeauty updateBeauty = new UserInfoBeauty();
//            updateBeauty.setStatus(BeautyAccountStatusEnum.USEED.getStatus());
//            this.userInfoBeautyMapper.updateById(updateBeauty, beautyAccount.getId());
//        }
//        //创建机器人好友
//        userContactService.addContact4Robot(userId);
    }
}

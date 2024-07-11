package com.starchat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.starchat.entity.User;
import com.starchat.exception.BusinessException;
import com.starchat.mapper.UserMapper;
import com.starchat.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
//        Date curDate = new Date();
//        String userId = StringTools.getUserId();
//
//        //查询邮箱是否需要设置靓号
//        UserInfoBeauty beautyAccount = this.userInfoBeautyMapper.selectByEmail(email);
//        Boolean useBeautyAccount = null != beautyAccount && BeautyAccountStatusEnum.NO_USE.getStatus().equals(beautyAccount.getStatus());
//        if (useBeautyAccount) {
//            userId = UserContactTypeEnum.USER.getPrefix() + beautyAccount.getUserId();
//        }
//        userInfo = new UserInfo();
//        userInfo.setUserId(userId);
//        userInfo.setNickName(nickName);
//        userInfo.setEmail(email);
//        userInfo.setPassword(StringTools.encodeByMD5(password));
//        userInfo.setCreateTime(curDate);
//        userInfo.setStatus(UserStatusEnum.ENABLE.getStatus());
//        userInfo.setLastOffTime(curDate.getTime());
//        this.userInfoMapper.insert(userInfo);
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

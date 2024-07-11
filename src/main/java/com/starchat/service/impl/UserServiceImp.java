package com.starchat.service.impl;

import com.starchat.entity.User;
import com.starchat.mapper.UserMapper;
import com.starchat.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * User Service Impl
 *
 * @author Jack
 */
@Service
public class UserServiceImp extends ServiceImpl<UserMapper, User> implements UserService {

}

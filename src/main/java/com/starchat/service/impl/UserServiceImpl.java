package com.starchat.service.impl;

import com.starchat.entity.User;
import com.starchat.mapper.UserMapper;
import com.starchat.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * User Service Impl
 *
 * @author Jack
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}

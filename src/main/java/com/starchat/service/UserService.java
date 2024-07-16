package com.starchat.service;

import com.starchat.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.starchat.entity.dto.TokenUserDto;

/**
 * User Service
 *
 * @author Jack
 */
public interface UserService extends IService<User> {

    void register(String email, String nickname, String password);

    TokenUserDto login(String email, String password);
}

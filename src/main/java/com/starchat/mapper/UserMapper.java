package com.starchat.mapper;

import com.starchat.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * User Mapper Interface
 *
 * @author Jack
 */
public interface UserMapper extends BaseMapper<User> {

    User selectByEmail(String email);
}

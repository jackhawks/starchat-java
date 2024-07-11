package com.starchat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.starchat.entity.User;

/**
 * User Mapper Interface
 *
 * @author Jack
 */
public interface UserMapper extends BaseMapper<User> {

    User selectByEmail(String email);
}

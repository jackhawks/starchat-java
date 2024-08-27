package com.starchat.entity.mapping;


import com.starchat.entity.User;
import com.starchat.entity.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserVO userToUserVO(User user);
}

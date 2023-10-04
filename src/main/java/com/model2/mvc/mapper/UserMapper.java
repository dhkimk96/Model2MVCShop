package com.model2.mvc.mapper;

import com.model2.mvc.entity.UserEntity;
import com.model2.mvc.service.domain.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {
    User UserEntityToUser(UserEntity entity);
    UserEntity userToUserEntity(User user);
}

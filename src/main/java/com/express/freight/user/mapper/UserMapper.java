package com.express.freight.user.mapper;

import com.express.freight.user.dto.UserDto;
import com.express.freight.user.dto.UserEntity;
import com.express.freight.common.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper extends EntityMapper<UserDto, UserEntity> {

    UserMapper mapper = Mappers.getMapper(UserMapper.class);

}

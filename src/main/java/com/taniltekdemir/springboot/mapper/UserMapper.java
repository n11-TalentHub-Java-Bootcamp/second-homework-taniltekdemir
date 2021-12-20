package com.taniltekdemir.springboot.mapper;

import com.taniltekdemir.springboot.dto.UserDto;
import com.taniltekdemir.springboot.dto.UserSaveDto;
import com.taniltekdemir.springboot.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User convertUserDtoToUser(UserDto dto);

    UserDto convertUserToUserDto(User user);

    User convertUserSaveDtoToUser(UserSaveDto saveDto);
}

package com.example.devops.repository.mapper;

import com.example.devops.repository.dtos.UserDto;
import com.example.devops.repository.entity.AccountEntity;
import com.example.devops.repository.entity.UserEntity;
import com.example.devops.repository.request.UserRegisterRequest;
import com.example.devops.repository.response.UserRegisterResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto mapEntity2Dto(UserEntity userEntity);

    UserRegisterResponse mapEntityAndAccountEntity2UserRegisterResponse(UserEntity userEntity, AccountEntity accountEntity);

    @Mapping(source = "accountEntity.id", target = "accountId")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    UserEntity mapUserRegisterRequestAndAccountEntity2Entity(UserRegisterRequest userRegisterRequest, AccountEntity accountEntity);
}

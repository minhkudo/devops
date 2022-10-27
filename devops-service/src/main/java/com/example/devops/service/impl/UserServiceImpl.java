package com.example.devops.service.impl;

import com.example.devops.repository.UserRepository;
import com.example.devops.repository.entity.AccountEntity;
import com.example.devops.repository.entity.UserEntity;
import com.example.devops.repository.mapper.UserMapper;
import com.example.devops.repository.request.UserRegisterRequest;
import com.example.devops.repository.response.UserRegisterResponse;
import com.example.devops.service.AccountService;
import com.example.devops.service.UserService;
import com.example.devops.utils.exception.DataAlreadyExistsException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AccountService accountService;

    @Override
    public UserRegisterResponse createUser(UserRegisterRequest userRegisterRequest) throws DataAlreadyExistsException {
        AccountEntity accountEntity = accountService.checkAccountExists(userRegisterRequest.getUsername());
        if (accountEntity != null) {
            throw new DataAlreadyExistsException();
        }

        AccountEntity account = accountService.createAccount(userRegisterRequest.getUsername(), userRegisterRequest.getPassword());

        UserEntity userEntity = UserMapper.INSTANCE.mapUserRegisterRequestAndAccountEntity2Entity(userRegisterRequest, account);
        UserEntity user = userRepository.save(userEntity);
        return UserMapper.INSTANCE.mapEntityAndAccountEntity2UserRegisterResponse(user, account);
    }
}

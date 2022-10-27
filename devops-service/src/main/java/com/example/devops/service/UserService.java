package com.example.devops.service;

import com.example.devops.repository.request.UserRegisterRequest;
import com.example.devops.repository.response.UserRegisterResponse;
import com.example.devops.utils.exception.DataAlreadyExistsException;

public interface UserService {
    UserRegisterResponse createUser(UserRegisterRequest userRegisterRequest) throws DataAlreadyExistsException;

}

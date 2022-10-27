package com.example.devops.service;

import com.example.devops.repository.entity.AccountEntity;

public interface AccountService {

    AccountEntity createAccount(String username, String password);

    AccountEntity checkAccountExists(String username);
}

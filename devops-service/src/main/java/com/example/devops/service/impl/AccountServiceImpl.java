package com.example.devops.service.impl;

import com.example.devops.repository.AccountRepository;
import com.example.devops.repository.entity.AccountEntity;
import com.example.devops.service.AccountService;
import com.example.devops.utils.status.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AccountEntity createAccount(String username, String password) {
        return accountRepository.save(new AccountEntity(username, passwordEncoder.encode(password), StatusEnum.ACTIVE.name()));
    }

    @Override
    public AccountEntity checkAccountExists(String username) {
        return accountRepository.findAccountEntityByUsername(username);
    }
}

package com.example.devops.service.impl;

import com.example.devops.repository.AccountRepository;
import com.example.devops.repository.entity.AccountEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountEntity accountEntity = accountRepository.findAccountEntityByUsername(username);
        if (accountEntity == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new JwtUserDetailsImpl(accountEntity);
    }
}

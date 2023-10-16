package com.wany.myuestcbbs.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wany.myuestcbbs.entity.Account;
import com.wany.myuestcbbs.mapper.AccountMapper;
import com.wany.myuestcbbs.service.AccountService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Override
    public boolean login(String name, String password) {
        Account account = baseMapper.findByName(name);
        if (account == null) {
            return false;
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password, account.getPassword());
    }

    @Override
    public boolean register(String name, String password) {
        Account account = baseMapper.findByName(name);
        if (account != null) {
            return false;
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(password);

        account = new Account();
        account.setName(name);
        account.setPassword(hashedPassword);
        return baseMapper.insert(account) > 0;
    }


}

package com.wany.myuestcbbs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wany.myuestcbbs.entity.Account;

public interface AccountService extends IService<Account> {
    boolean login(String name, String password);

    boolean register(String name, String password);
}

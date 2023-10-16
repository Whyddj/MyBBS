package com.wany.myuestcbbs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wany.myuestcbbs.entity.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper extends BaseMapper<Account> {
    Account findByName(String name);
}

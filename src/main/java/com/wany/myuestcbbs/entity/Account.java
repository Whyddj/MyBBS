package com.wany.myuestcbbs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Account {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String password;
}

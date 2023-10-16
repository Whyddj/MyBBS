package com.wany.myuestcbbs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class Post {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    private String content;
    private String author;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp time;
}
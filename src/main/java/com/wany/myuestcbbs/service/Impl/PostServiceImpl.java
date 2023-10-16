package com.wany.myuestcbbs.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wany.myuestcbbs.entity.Post;
import com.wany.myuestcbbs.mapper.PostMapper;
import com.wany.myuestcbbs.service.PostService;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {

}

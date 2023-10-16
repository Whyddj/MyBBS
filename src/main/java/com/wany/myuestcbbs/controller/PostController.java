package com.wany.myuestcbbs.controller;

import com.wany.myuestcbbs.entity.Post;
import com.wany.myuestcbbs.entity.Result;
import com.wany.myuestcbbs.entity.ResultCode;
import com.wany.myuestcbbs.service.PostService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/v1/posts")
public class PostController {
    @Resource
    private PostService postService;

    @GetMapping("/{id}")
    public Result getPostById(@PathVariable String id) {
        Post post = postService.getById(id);
        if (post == null) {
            return Result.FAIL(ResultCode.NOT_FOUND);
        }
        return Result.SUCCESS(postService.getById(id));
    }

    @PostMapping("/")
    public Result createPost(@RequestBody Post post) {
        post.setId(null);
        if (post.getTitle().length() > 255 || post.getAuthor().length() > 255) {
            return Result.FAIL("param is invalid");
        }
        postService.save(post);
        return Result.SUCCESS("Post " + post.getId() + " is created.");
    }

    @PutMapping("/{id}")
    public Result updatePostById(@PathVariable String id, @RequestBody Post post) {
        // id转为int
        if (post.getTitle().length() > 255 || post.getAuthor().length() > 255) {
            return Result.FAIL("param is invalid");
        }
        int id_ = Integer.parseInt(id);
        post.setId(id_);
        if (!postService.updateById(post)) {
            return Result.FAIL(ResultCode.NOT_FOUND);
        }
        return Result.SUCCESS("Post " + id + " is updated.");
    }

    @DeleteMapping("/{id}")
    public Result deletePostById(@PathVariable String id) {
        if (!postService.removeById(id)) {
            return Result.FAIL(ResultCode.NOT_FOUND);
        }
        return Result.SUCCESS("Post " + id + " is deleted.");
    }

//    @GetMapping("/{id}")
//    public Result getPostById(@PathVariable String id) {
//        return Result.SUCCESS("Post " + id + " is returned.");
//    }
//
//    @PostMapping("/")
//    public Result createPost() {
//        return Result.SUCCESS("Post is created.");
//    }
//
//    @PutMapping("/{id}")
//    public Result updatePostById(@PathVariable String id) {
//        return Result.SUCCESS("Post " + id + " is updated.");
//    }
//
//    @DeleteMapping("/{id}")
//    public Result deletePostById(@PathVariable String id) {
//        return Result.SUCCESS("Post " + id + " is deleted.");
//    }
}

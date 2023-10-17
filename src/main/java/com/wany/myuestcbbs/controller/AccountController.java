package com.wany.myuestcbbs.controller;

import com.wany.myuestcbbs.entity.Result;
import com.wany.myuestcbbs.entity.ResultCode;
import com.wany.myuestcbbs.service.AccountService;
import com.wany.myuestcbbs.util.JwtUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/accounts")
public class AccountController {
    @Resource
    private AccountService accountService;

    @GetMapping("/login/{name}/{password}")
    public Result login(@PathVariable String name, @PathVariable String password) {
        if (accountService.login(name, password)) {
            Map<String, String> payload = new HashMap<>();
            payload.put("name", name);
//            payload.put("password", password); // 存放密码是不安全的

            String token = JwtUtil.getToken(payload);

            return Result.SUCCESS(token);
        }
        return Result.FAIL("Login failed.");
    }

    @PostMapping("/register/{name}/{password}")
    public Result register(@PathVariable String name, @PathVariable String password) {
        if (accountService.register(name, password)) {
            return Result.SUCCESS("Register successfully.");
        }
        return Result.FAIL("Register failed.");
    }
}

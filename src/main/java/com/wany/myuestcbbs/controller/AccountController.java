package com.wany.myuestcbbs.controller;

import com.wany.myuestcbbs.entity.Result;
import com.wany.myuestcbbs.entity.ResultCode;
import com.wany.myuestcbbs.service.AccountService;
import com.wany.myuestcbbs.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Api(value = "AccountController", tags = "用户接口")
@RestController
@RequestMapping("/v1/accounts")
public class AccountController {
    @Resource
    private AccountService accountService;

    @ApiOperation("登录")
    @ApiResponses({
            @ApiResponse(code = 200, message = "{Token}", response = Result.class),
            @ApiResponse(code = 400, message = "Login failed.", response = Result.class)
    })
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

    @ApiOperation("注册")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Register successfully.", response = Result.class),
            @ApiResponse(code = 400, message = "Register failed.", response = Result.class)
    })
    @PostMapping("/register/{name}/{password}")
    public Result register(@PathVariable String name, @PathVariable String password) {
        if (accountService.register(name, password)) {
            return Result.SUCCESS("Register successfully.");
        }
        return Result.FAIL("Register failed.");
    }
}

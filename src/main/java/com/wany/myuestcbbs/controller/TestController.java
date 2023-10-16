package com.wany.myuestcbbs.controller;


import com.wany.myuestcbbs.entity.Result;
import com.wany.myuestcbbs.entity.ResultCode;
import com.wany.myuestcbbs.entity.TestDO;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TestController {
    //url参数中附带了name和type两个参数
    @GetMapping("/test")
    public String test(@RequestParam String name, String type) {
        return name + "-" + type;
    }

    //body中附带了name和type两个参数
    @PostMapping("/test2")
    public String test2(@RequestBody TestDO testDO) {
        return testDO.getName() + "-" + testDO.getType();
    }

    //从request中获取header中的auth参数
    @PostMapping("/test3")
    public String test2(@RequestBody TestDO testDO, HttpServletRequest request) {
        if (request.getHeader("auth") == null) return "Invalid User";
        return testDO.getName() + "-" + testDO.getType();

    }

    //封装返回结果
    @PostMapping("/test4")
    public Result test4(@RequestBody TestDO testDO, HttpServletRequest request) {
        if (request.getHeader("auth") == null) {
            return Result.FAIL(ResultCode.UNAUTHORIZED);
        }
        return Result.SUCCESS(testDO);
    }
}

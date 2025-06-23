package com.java.aitest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController 注解表明这是一个处理Web请求的控制器类
@RestController
public class HelloController {

    // @GetMapping("/") 让这个方法处理对根路径 "/" 的GET请求
    @GetMapping("/")
    public String index() {
        // 当用户访问 http://localhost:8080/ 时，返回这条消息
        return "欢迎来到AI智能题库后端！请访问 /index.html 或 /login.html 查看页面。";
    }

    // @GetMapping("/hello") 让这个方法处理对 "/hello" 路径的GET请求
    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
}
package com.java.aitest.Controller;

import com.java.aitest.Dto.UserLoginDto;
import com.java.aitest.Dto.UserRegistrationDto;
import com.java.aitest.Entity.User;
import com.java.aitest.Service.UserService;
import com.java.aitest.Vo.Result;
import com.java.aitest.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public Result<String> registerUser(@RequestBody UserRegistrationDto registrationDto) {
        try {
            userService.registerNewUser(registrationDto);
            return Result.success("用户注册成功!", null);
        } catch (IllegalArgumentException e) {
            return Result.fail(e.getMessage());
        }
    }

    @PostMapping("/login")
    public Result<String> loginUser(@RequestBody UserLoginDto loginDto) {
        try {
            User user = userService.findByUsername(loginDto.getUsername());
            if (passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
                // 登录成功，生成JWT
                final String token = jwtUtil.generateToken(user);
                // 将Token返回给前端
                return Result.success("登录成功", token);
            } else {
                return Result.fail("密码错误");
            }
        } catch (IllegalArgumentException e) {
            return Result.fail("用户名不存在");
        }
    }
}
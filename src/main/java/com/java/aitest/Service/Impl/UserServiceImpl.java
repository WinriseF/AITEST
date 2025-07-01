package com.java.aitest.Service.Impl;

import com.java.aitest.Dao.UserDao;
import com.java.aitest.Dto.UserRegistrationDto;
import com.java.aitest.Entity.User;
import com.java.aitest.Service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao, @Lazy PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerNewUser(UserRegistrationDto registrationDto) {
        // 检查用户名是否已存在
        if (userDao.existsByUsername(registrationDto.getUsername())) {
            throw new IllegalArgumentException("错误: 该用户名已被注册!");
        }

        // 检查邮箱是否已存在
        if (userDao.existsByEmail(registrationDto.getEmail())) {
            throw new IllegalArgumentException("错误: 该邮箱已被注册!");
        }

        User newUser = new User();
        newUser.setUsername(registrationDto.getUsername());
        // 使用PasswordEncoder加密密码
        newUser.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        newUser.setEmail(registrationDto.getEmail());

        return userDao.save(newUser);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("未找到用户: " + username));
    }
}
package com.java.aitest.Service;

import com.java.aitest.Dto.UserRegistrationDto;
import com.java.aitest.Entity.User;

public interface UserService {
    /**
     * 注册新用户
     * @param registrationDto 包含用户注册信息的DTO
     * @return 创建的用户实体
     */
    User registerNewUser(UserRegistrationDto registrationDto);

    /**
     * 根据用户名查找用户
     * @param username 用户名
     * @return 用户实体，如果不存在则返回null或抛出异常
     */
    User findByUsername(String username);
}
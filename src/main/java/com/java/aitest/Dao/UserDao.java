package com.java.aitest.Dao;

import com.java.aitest.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    /**
     * 根据用户名查找用户
     * @param username 用户名
     * @return 返回一个可能包含用户的Optional对象
     */
    Optional<User> findByUsername(String username);

    /**
     * 检查用户名是否存在
     * @param username 用户名
     * @return 如果存在返回true，否则返回false
     */
    boolean existsByUsername(String username);

    /**
     * 检查邮箱是否存在
     * @param email 邮箱
     * @return 如果存在返回true，否则返回false
     */
    boolean existsByEmail(String email);
}
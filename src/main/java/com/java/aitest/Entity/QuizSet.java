package com.java.aitest.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "quiz_sets")
public class QuizSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 作为与 QuestionEntity 关联的唯一标识符
    @Column(unique = true, nullable = false)
    private String generateId;

    // 题库的标题，例如 "Java 并发编程"
    @Column(nullable = false)
    private String title;

    // 题库的分类，例如 "IT 技术"
    @Column(nullable = false)
    private String category;

    // 该题库包含的总题目数
    private int totalQuestions;

    // 创建时间
    private LocalDateTime createdAt;

    // 在实体被持久化之前，自动设置创建时间
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}

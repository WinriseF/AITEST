package com.java.aitest.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
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

    // --- Getters and Setters ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenerateId() {
        return generateId;
    }

    public void setGenerateId(String generateId) {
        this.generateId = generateId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

package com.java.aitest.Entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @author Winrisef
 * @see <a href="https://github.com/WinriseF">https://github.com/WinriseF</a>
 * date 2025/6/26
 * description:
 */
@Entity
@Data
@Table(name = "questions") // 数据库表名
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 主键自增
    private Long id;
    private String generateId; // 生成题目的唯一标识符
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String answer;
    private String analysis;
}
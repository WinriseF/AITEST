package com.java.aitest.Dao;

import com.java.aitest.Entity.QuizSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizSetDao extends JpaRepository<QuizSet, Long> {
    boolean existsByGenerateId(String generateId);
    List<QuizSet> findAllByOrderByCreatedAtDesc();
}

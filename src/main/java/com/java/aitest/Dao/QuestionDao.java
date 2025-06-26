package com.java.aitest.Dao;

import com.java.aitest.Entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Winrisef
 * @see <a href="https://github.com/WinriseF">https://github.com/WinriseF</a>
 * date 2025/6/26
 * description:
 */
@Repository
public interface QuestionDao extends JpaRepository<QuestionEntity, Long> {
    List<QuestionEntity> findQuestionEntitiesByGenerateId(String generateId);
}

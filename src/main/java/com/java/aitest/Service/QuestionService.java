package com.java.aitest.Service;

import com.java.aitest.Dto.GenerateQuestionDto;
import com.java.aitest.Entity.QuestionEntity;
import com.java.aitest.Vo.QuestionVo;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @author Winrisef
 * @see <a href="https://github.com/WinriseF">https://github.com/WinriseF</a>
 * date 2025/6/26
 * description:
 */
public interface QuestionService {
    Boolean saveQuestion(List<QuestionEntity> questionEntities);

    Future<Boolean> GenerateQuestion(GenerateQuestionDto questionDto, String uuid);

    List<QuestionVo> getQuestionByGenerateId(String generateId);
}

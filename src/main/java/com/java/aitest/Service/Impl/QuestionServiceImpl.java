package com.java.aitest.Service.Impl;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.aitest.Dao.QuestionDao;
import com.java.aitest.Dto.GenerateQuestionDto;
import com.java.aitest.Entity.QuestionEntity;
import com.java.aitest.Service.QuestionService;
import com.java.aitest.Vo.QuestionVo;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @author Winrisef
 * @see <a href="https://github.com/WinriseF">https://github.com/WinriseF</a>
 * date 2025/6/26
 * description:
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionDao questionDao;

    @Autowired
    ChatClient chatClient;

    @Override
    public Boolean saveQuestion(List<QuestionEntity> questionEntities) {
        // 实现保存问题的逻辑
        // 这里可以调用 QuestionDao 的方法来保存问题
        questionDao.saveAll(questionEntities);
        return true; // 返回保存结果
    }

    @Override
    @Async
    public Future<Boolean> GenerateQuestion(GenerateQuestionDto questionDto, String uuid) {
        // 实现生成问题的逻辑
        // 这里可以调用相关的服务或方法来生成问题
        // 返回一个 Future 对象，表示异步操作的结果
        String content = chatClient.prompt()
                .system("你是一个精通各行各业的专家，擅长针对各行各业进行出题，考试，题目解析等。")
                .user("帮我出" + questionDto.getQuestionNum() + "道" + questionDto.getQuestion() + "的题目，难度要求为" +questionDto.getDifficulty() + questionDto.getQuestionFormat())
                .call()
                .content();
        content = content.replaceAll("```json", "").replace("```", "");
        List<QuestionEntity> questionEntities = JSON.parseArray(content, QuestionEntity.class);
        questionEntities.forEach(questionEntity -> questionEntity.setGenerateId(uuid));
        saveQuestion(questionEntities);
        return AsyncResult.forValue(true); // 需要实现具体的异步逻辑
    }

    @Override
    public List<QuestionVo> getQuestionByGenerateId(String generateId) {
        List<QuestionEntity> questionEntities = questionDao.findQuestionEntitiesByGenerateId(generateId);
        if(questionEntities == null || questionEntities.isEmpty()){
            throw new RuntimeException("没有找到对应的题目");
        }
        List<QuestionVo> list = questionEntities.stream().map(item -> {
            QuestionVo questionVo = new QuestionVo();
            BeanUtils.copyProperties(item, questionVo);
            return questionVo;
        }).toList();
        return list;
    }
}

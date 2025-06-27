package com.java.aitest.Controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.java.aitest.Dto.GenerateQuestionDto;
import com.java.aitest.Entity.QuestionEntity;
import com.java.aitest.Service.QuestionService;
import com.java.aitest.Vo.QuestionVo;
import com.java.aitest.Vo.Result;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/question") // 类上的url映射路径
public class QuestionController {
    @Autowired
    ChatClient chatClient;

    @Autowired
    QuestionService questionService;

    /**
     * 生成题目
     * @param QuestionDto
     * @return
     * @throws JsonProcessingException
     */
    @PostMapping("generateQuestion") // 这是方法上的url映射路径
    public String question(@RequestBody GenerateQuestionDto QuestionDto) throws JsonProcessingException {
        String content = chatClient.prompt()
                .system("你是一个精通各行各业的专家，擅长针对各行各业进行出题，考试，题目解析等。")
                .user("帮我出" + QuestionDto.getQuestionNum() + "道" + QuestionDto.getQuestion() + "的题目，" + QuestionDto.getQuestionFormat())
                .call()
                .content();
        content = content.replaceAll("```json", "").replace("```", "");
        ObjectMapper mapper = new ObjectMapper();
        List<QuestionEntity> questionEntities = mapper.readValue(content, new TypeReference<List<QuestionEntity>>() {});

        Boolean bool = questionService.saveQuestion(questionEntities);
        return content;
    }

    @PostMapping("asyncGenerateQuestion")
    public Result asyncGenerateQuestion(@RequestBody GenerateQuestionDto questionDto){
        String generateId = questionDto.getGenerateId();
        if (generateId == null || generateId.isEmpty()) {
            generateId = UUID.randomUUID().toString();
        }
        // 将这个确定的ID传递给服务层，启动一个异步的生成任务
        questionService.GenerateQuestion(questionDto, generateId);
        // 将这个批次ID返回给前端，以便前端可以根据此ID轮询结果
        return Result.success("题目生成请求已提交", generateId);
    }

    @GetMapping("question/{generateId}")
    public Result<List<QuestionVo>> getQuestionByGenerateId(@PathVariable("generateId") String generateId) {
        List<QuestionVo> questionVos = questionService.getQuestionByGenerateId(generateId);
        return Result.success("查询成功", questionVos);
    }
}
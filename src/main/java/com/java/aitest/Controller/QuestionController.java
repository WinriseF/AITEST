package com.java.aitest.Controller;

import com.java.aitest.Dto.GenerateQuestionDto;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/question") // 类上的url映射路径
public class QuestionController {
    @Autowired
    ChatClient chatClient;

    @PostMapping("generateQuestion") // 这是方法上的url映射路径
    public String question(@RequestBody GenerateQuestionDto QuestionDto) {
        return chatClient.prompt()
                .system("你是一个精通各行各业的专家，擅长针对各行各业进行出题，考试，题目解析等。")
                .user("帮我出" + QuestionDto.getQuestionNum() + "道" + QuestionDto.getQuestion() + "的题目，" + QuestionDto.getQuestionFormat())
                .call()
                .content();
    }
}
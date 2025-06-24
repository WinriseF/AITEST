package com.java.aitest.Controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/chat") // 类上的url映射路径
public class ChatController {
    @Autowired // 自动根据类型从容器中去获取对应的对象并注入到这个变量中
    ChatClient chatClient;
    @GetMapping("/chat") //这是方法上的url映射路径
    public String chat(@RequestParam("msg") String msg){
        return chatClient.prompt().user(msg).call().content();
    }
    @GetMapping(value = "streamChat",produces = "text/html;charset=utf-8")
    public Flux<String> streamChat(@RequestParam("msg") String msg){
        return chatClient.prompt().user(msg).stream().content();
    }
}

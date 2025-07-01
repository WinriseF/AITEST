package com.java.aitest.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author Winrisef
 * @see <a href="https://github.com/WinriseF">https://github.com/WinriseF</a>
 * date 2025/6/26
 * description:
 */
@Configuration
@EnableAsync
public class TaskPoolConfig {
    @Bean("executor")
    public Executor executor() {
        int cunt = Runtime.getRuntime().availableProcessors();  //获取CPU核心数量
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(cunt * 5);
        executor.setMaxPoolSize(cunt*10);
        executor.setQueueCapacity(2000);
        executor.setKeepAliveSeconds(60);
        executor.setAwaitTerminationSeconds(60);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        return executor;
    }
}

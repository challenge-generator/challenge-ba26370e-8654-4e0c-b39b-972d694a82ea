package com.pragma.comments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.boot.task.TaskExecutorBuilder;
import java.util.concurrent.Executor;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableAsync
public class CommentsApplication {
    private final TaskExecutorBuilder taskExecutorBuilder;

    public CommentsApplication(TaskExecutorBuilder taskExecutorBuilder) {
        this.taskExecutorBuilder = taskExecutorBuilder;
    }

    public static void main(String[] args) {
        SpringApplication.run(CommentsApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean(name = "asyncExecutor")
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = taskExecutorBuilder.build();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("AsyncExecutor-");
        executor.initialize();
        return executor;
    }

    @Bean
    public String applicationInfo() {
        String env = System.getProperty("spring.profiles.active", "default");
        String version = getClass().getPackage().getImplementationVersion();
        String info = String.format("Comments Application - Environment: %s, Version: %s", env, version != null ? version : "0.0.1-SNAPSHOT");
        System.out.println(info);
        return info;
    }
}
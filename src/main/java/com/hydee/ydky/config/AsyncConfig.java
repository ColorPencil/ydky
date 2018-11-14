package com.hydee.ydky.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@SpringBootConfiguration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

    @Value("${spring.datasource.corePoolSize}")
    private int corePoolSize;

    @Value("${spring.datasource.maxPoolSize}")
    private int maxPoolSize;

    @Value("${spring.datasource.queueCapacity}")
    private int queueCapacity;

    private static final Logger logger = LoggerFactory.getLogger(AsyncConfig.class);

    @Override
    public Executor getAsyncExecutor() {
        logger.info("线程配置："+corePoolSize+"-"+maxPoolSize+"-"+queueCapacity);
        VisiableThreadPoolTaskExecutor visiableThreadPoolTaskExecutor = new VisiableThreadPoolTaskExecutor();
        visiableThreadPoolTaskExecutor.setCorePoolSize(corePoolSize);//设置核心池大小
        visiableThreadPoolTaskExecutor.setMaxPoolSize(maxPoolSize);//设置最大池大小
        visiableThreadPoolTaskExecutor.setQueueCapacity(queueCapacity);//设置QueueCapacity容量
        visiableThreadPoolTaskExecutor.initialize();
        return visiableThreadPoolTaskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }
}

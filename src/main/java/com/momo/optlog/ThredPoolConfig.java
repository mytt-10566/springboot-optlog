package com.momo.optlog;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@AutoConfigureBefore(TaskExecutionAutoConfiguration.class)
public class ThredPoolConfig {

    /**
     * 线程池维护线程的最少数量
     */
    private static final int THREAD_POOL_CORE_POOL_SIZE = 30;

    /**
     * 线程池维护线程的最大数量
     */
    private static final int THREAD_POOL_MAX_POOL_SIZE = 30;

    /**
     * 缓存队列
     */
    private static final int THREAD_POOL_QUEUE_CAPACITY = 8;

    /**
     * 允许的空闲时间，单位s
     */
    private static final int THREAD_POOL_KEEP_ALIVE = 60;

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(THREAD_POOL_CORE_POOL_SIZE);
        executor.setMaxPoolSize(THREAD_POOL_MAX_POOL_SIZE);
        executor.setQueueCapacity(THREAD_POOL_QUEUE_CAPACITY);
        executor.setThreadNamePrefix("taskExecutor-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        executor.setKeepAliveSeconds(THREAD_POOL_KEEP_ALIVE);
        executor.initialize();
        return executor;
    }
}

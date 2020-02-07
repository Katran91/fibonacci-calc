package com.stratij.proxy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@TestConfiguration
public class TestConfig {
    @Value("${fibonacci.proxy.thread-number:1}")
    private int numberOfThreads;

    @Bean("fixedThreadPool")
    public ExecutorService fixedThreadPool() {
        return Executors.newFixedThreadPool(numberOfThreads);
    }
}

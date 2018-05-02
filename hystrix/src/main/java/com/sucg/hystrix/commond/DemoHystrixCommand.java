package com.sucg.hystrix.commond;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.sucg.filter.StartApplicationFilter;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

public class DemoHystrixCommand extends HystrixCommand<String> {
    private static final Logger logger = LoggerFactory.getLogger(DemoHystrixCommand.class);
    private RestTemplate restTemplate;
    private static final Setter cachedSetter =
            Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("HystrixAPI"))
                    .andCommandKey(HystrixCommandKey.Factory.asKey("HystrixAPI"));
    public static final String URL_API_DEMO = "api-demo";
    private String api;
    private String appName;

    public DemoHystrixCommand(String appName, String api, RestTemplate restTemplate) {
        super(cachedSetter);
        this.api = api;
        this.appName = appName;
        this.restTemplate = restTemplate;
        HystrixRequestContext.initializeContext();
}

    @Override
    protected String run() {
        logger.error(this.toString() + ":" + Thread.currentThread().getId());
        ResponseEntity<String> response =  restTemplate.getForEntity(String.format("http://%s/%s", URL_API_DEMO, api), String.class);
        return response.getBody();
    }

    @Override
    protected String getFallback() {
        return String.format("error:%s:[%s]", appName, StartApplicationFilter.appPort);
    }

    @Override
    public String toString() {
        return "DemoHystrixCommand{" +
                "threadPoolKey=" + threadPoolKey.name() +
                ", commandKey=" + commandKey.name() +
                ", commandGroup=" + commandGroup.name() +
                '}';
    }
}

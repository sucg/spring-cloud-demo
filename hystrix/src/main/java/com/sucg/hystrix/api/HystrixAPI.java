package com.sucg.hystrix.api;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sucg.hystrix.commond.DemoHystrixCommand;
import com.sucg.springclouddemo.filter.StartApplicationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import static java.lang.String.format;

@RestController
public class HystrixAPI {

    @Value("${spring.application.name}")
    private String appName;

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/test", method = {RequestMethod.POST, RequestMethod.GET})
    public String test() {
        return format("%s:%s:[%s]", appName, StartApplicationFilter.appPort, "test");
    }

    @RequestMapping(value = "/test1", method = {RequestMethod.POST, RequestMethod.GET})
    public String test1() {
        String response = new DemoHystrixCommand(appName, "test1", restTemplate).execute();
        return format("%s:%s:[%s]", appName, StartApplicationFilter.appPort, response);
    }

    @RequestMapping(value = "/test2", method = {RequestMethod.POST, RequestMethod.GET})
    public String test2() {
        String response = new DemoHystrixCommand(appName,"test2", restTemplate).execute();
        return format("%s:%s:[%s]", appName, StartApplicationFilter.appPort, response);
    }

    @RequestMapping(value = "/test3", method = {RequestMethod.POST, RequestMethod.GET})
    @HystrixCommand(fallbackMethod = "fallBack")
    public String test3() {
        return restTemplate.getForEntity(String.format("http://%s/%s",
                DemoHystrixCommand.URL_API_DEMO, "test2"), String.class).getBody();
    }

    private String fallBack() {
        return String.format("fallback:%s:[%s]", appName, StartApplicationFilter.appPort);
    }
}

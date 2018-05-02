package com.sucg.api.api;

import com.sucg.springclouddemo.filter.StartApplicationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoAPI {

    @Value("${spring.application.name}")
    private String appName;

    @RequestMapping("test")
    public String test() {
       return String.format("%s:%s:[%s]", appName, StartApplicationFilter.appPort, "test");
    }

    @RequestMapping("test1")
    public String test1() {
        return String.format("%s:%s:[%s]", appName, StartApplicationFilter.appPort, "test1");
    }

    @RequestMapping("test2")
    public String test2() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return String.format("%s:%s:[%s]", appName, StartApplicationFilter.appPort, "test2");
    }


}

package com.sucg.api.api;


import com.sucg.filter.StartApplicationFilter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trace")
public class TraceAPI {

    private Logger log = Logger.getLogger(getClass());

    @Value("${spring.application.name}")
    private String appName;

    @RequestMapping("/test")
    public String test() {
        log.info(String.format("===<call %s, test>===", appName));
        return String.format("%s:%s:[%s]", appName, StartApplicationFilter.appPort, "test");
    }

    @RequestMapping("/test1")
    public String test1() {
        log.info(String.format("===<call %s, test1>===", appName));
        return String.format("%s:%s:[%s]", appName, StartApplicationFilter.appPort, "test1");
    }

    @RequestMapping("/test2")
    public String test2() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(String.format("===<call %s, test2>===", appName));
        return String.format("%s:%s:[%s]", appName, StartApplicationFilter.appPort, "test2");
    }



}

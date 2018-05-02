package com.sucg.ribbon.api;

import com.sucg.springclouddemo.filter.StartApplicationFilter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/trace")
public class TraceAPI {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${spring.application.name}")
    private String appName;

    private Logger log = Logger.getLogger(getClass());

    @RequestMapping("/test")
    public String test() {
        log.info(String.format("===<call %s, test>===", appName));
        ResponseEntity<String> response = restTemplate.getForEntity("http://api-demo/trace/test1", String.class);
        return String.format("%s:%s:[%s]", appName, StartApplicationFilter.appPort, response.getBody());
    }

    @RequestMapping("/test1")
    public String test1() {
        log.info(String.format("===<call %s, test1>===", appName));
        ResponseEntity<String> response = restTemplate.getForEntity("http://api-demo/trace/test1", String.class);
        return String.format("%s:%s:[%s]", appName, StartApplicationFilter.appPort, response.getBody());
    }

    @RequestMapping("/test2")
    public String test2() {
        log.info(String.format("===<call %s, test2>===", appName));
        ResponseEntity<String> response = restTemplate.getForEntity("http://api-demo/trace/test2", String.class);
        return String.format("%s:%s:[%s]", appName, StartApplicationFilter.appPort, response.getBody());
    }



}

package com.sucg.ribbon.api;

import com.sucg.filter.StartApplicationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RefreshScope
public class RibbonAPI {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${spring.application.name}")
    private String appName;
    @Value("${var}")
    private String var;

    @RequestMapping("var")
    public String var(){
        return String.format("%s:%s:[%s]", appName, var, "var");
    }

    @RequestMapping("test")
    public String test() {
        return String.format("%s:%s:[%s]", appName, StartApplicationFilter.appPort, "test");
    }

    @RequestMapping("test1")
    public String test1() {
        ResponseEntity<String> response = restTemplate.getForEntity("http://api-demo/test1", String.class);
        return String.format("%s:%s:[%s]", appName, StartApplicationFilter.appPort, response.getBody());
    }

    @RequestMapping("test2")
    public String test2() {
        ResponseEntity<String> response = restTemplate.getForEntity("http://api-demo/test2", String.class);
        return String.format("%s:%s:[%s]", appName, StartApplicationFilter.appPort, response.getBody());
    }



}

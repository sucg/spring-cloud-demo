package com.sucg.feign;

import com.sucg.filter.StartApplicationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignDemoAPI {

    @Value("${spring.application.name}")
    private String appName;

    @Autowired
    private IFeignClient feignClient;

    @RequestMapping("test")
    public String test() {
        return String.format("%s:%s:[%s]", appName, StartApplicationFilter.appPort, "test");
    }

    @RequestMapping("test1")
    public String test1() {
        return String.format("%s:%s:[%s]", appName, StartApplicationFilter.appPort, feignClient.test1());
    }

    @RequestMapping("test2")
    public String test2()  {
        return String.format("%s:%s:[%s]", appName, StartApplicationFilter.appPort, feignClient.test2());
    }
}

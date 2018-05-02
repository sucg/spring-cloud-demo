package com.sucg.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("api-demo")
public interface IFeignClient {

    @RequestMapping("test1")
    String test1();

    @RequestMapping("test2")
    String test2();
}

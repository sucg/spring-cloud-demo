package com.sucg.zk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;

@EnableDiscoveryClient
@RestController
@SpringBootApplication
public class ZkApplication {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @RequestMapping("/test")
    public String test() {
        return "Hello World";
    }

    @RequestMapping("/test1")
    public Object test1() {
        return loadBalancerClient.choose("zk-demo");
    }

    @RequestMapping("/test2")
    public Object test2() {
        return discoveryClient.getInstances("zk-demo");
    }

    @RequestMapping("/test3")
    public Object test3() {
       return restTemplate.getForEntity("http://zk-demo/test", String.class).getBody();
    }

    public static void main(String[] args) {
        SpringApplication.run(ZkApplication.class, args);
    }
}

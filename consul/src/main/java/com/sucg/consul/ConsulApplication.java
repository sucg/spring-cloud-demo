package com.sucg.consul;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
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

@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class ConsulApplication {

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
        return "Hello world";
    }

    @RequestMapping("/test1")
    public Object test1() {
        return loadBalancerClient.choose("consul-demo");
//        ServiceInstance serviceInstance = loadBalancer.choose("consul-demo");
//        return restTemplate.getForEntity(String.format("%s/test", serviceInstance.getUri()), String.class).getBody();
    }

    @RequestMapping("/test2")
    public Object test2() {
        ServiceInstance serviceInstance = discoveryClient.getInstances("consul-demo").get(0);
        return serviceInstance;
//        return restTemplate.getForEntity(String.format("%s/test", serviceInstance.getUri()), String.class).getBody();
    }

    @RequestMapping("/test3")
    public Object test3() {
        return restTemplate.getForEntity("http://consul-demo/test", String.class).getBody();
//        ServiceInstance serviceInstance = loadBalancer.choose("consul-demo");
//        return restTemplate.getForEntity(String.format("%s/test", serviceInstance.getUri()), String.class).getBody();
    }


    public static void main(String[] args) {
        SpringApplication.run(ConsulApplication.class, args);
    }
}

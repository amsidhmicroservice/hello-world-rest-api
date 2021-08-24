package com.amsidh.mvc.helloworldrestapi;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
@RestController
public class HelloWorldRestApiApplication {

    private final InstanceInformationService instanceInformationService;

    public static void main(String[] args) {
        log.info("Inside main method of HelloWorldRestApiApplication");
        SpringApplication.run(HelloWorldRestApiApplication.class, args);
    }

    @GetMapping
    public String imUpAndRunning() {
        log.info("Inside imUpAndRunning method of HelloWorldRestApiApplication");
        return "{healthy:true}";
    }

    @GetMapping("/sayHello")
    public String sayHello() {
        log.info("Inside sayHello method of HelloWorldRestApiApplication");
        return "Application running on pod" + instanceInformationService.retrieveInstanceInfo();
    }

    @Bean
    public HttpTraceRepository getHttpTraceRepository(){
        return new InMemoryHttpTraceRepository();
    }
}

@Slf4j
@Service
class InstanceInformationService {
    private static final String HOST_NAME = "HOSTNAME";

    private static final String DEFAULT_ENV_INSTANCE_GUID = "LOCAL";

    @Value("${" + HOST_NAME + ":" + DEFAULT_ENV_INSTANCE_GUID + "}")
    private String hostName;

    public String retrieveInstanceInfo() {
        log.info("Inside retrieveInstanceInfo method of InstanceInformationService");
        return hostName.substring(hostName.length());
    }
}

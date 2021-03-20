package keanri828.ookamicroservices.analyseservice2;

import keanri828.ookamicroservices.analyseservice2.model.ConfigDto;
import keanri828.ookamicroservices.analyseservice2.services.AnalysService1Feign;
import keanri828.ookamicroservices.analyseservice2.services.AnalyseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@EnableFeignClients
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@RestController

public class AnalyseService2Application {

    public static void main(String[] args) {
        SpringApplication.run(AnalyseService2Application.class, args);
    }




    @Value("${spring.application.name}")
    private String appName;
    @Autowired
    AnalysService1Feign analysService1Feign;
    @Autowired
    AnalyseService analyseService;
    @PostMapping(
            value = "/analyse1",
            consumes = "application/json",
            produces = "application/json"
    )
    public Boolean analyseConfig(@RequestBody @Valid ConfigDto dto){
        return analyseService.analyseConfig(dto);
    }

    @GetMapping(
            value = "/state",
            produces = "application/json"
    )
    public String getState(){return  analyseService.getState();}
}

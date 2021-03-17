package keanri828.ookamicroservices.analyseservice1;

import keanri828.ookamicroservices.analyseservice1.model.ConfigDto;
import keanri828.ookamicroservices.analyseservice1.services.AnalyseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@RestController
public class AnalyseService1Application {

    public static void main(String[] args) {
        SpringApplication.run(AnalyseService1Application.class, args);
    }

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
            value = "/",
            produces = "application/json"
    )
    public String testString(){return "Test Erfolgreich MService UP";}

}

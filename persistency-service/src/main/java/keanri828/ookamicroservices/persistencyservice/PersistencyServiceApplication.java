package keanri828.ookamicroservices.persistencyservice;

import com.netflix.discovery.EurekaClient;
import keanri828.ookamicroservices.persistencyservice.databaseconf.ConfigService;
import keanri828.ookamicroservices.persistencyservice.entities.ConfigEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@RestController

public class PersistencyServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersistencyServiceApplication.class, args);
    }

    @Autowired
    ConfigService configService;

    @PostMapping(
            value = "/save",
            consumes = "application/json"
    )
    public void analyseConfig(@RequestBody ConfigEntity dto){

        configService.save(dto);
    }

}

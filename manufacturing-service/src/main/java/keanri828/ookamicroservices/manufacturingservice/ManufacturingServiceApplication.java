package keanri828.ookamicroservices.manufacturingservice;

import keanri828.ookamicroservices.manufacturingservice.model.ConfigDto;
import keanri828.ookamicroservices.manufacturingservice.services.ManufacturingService;
import keanri828.ookamicroservices.manufacturingservice.services.ManufacturingServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.UUID;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@RestController
public class ManufacturingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManufacturingServiceApplication.class, args);
    }

    @Autowired
    ManufacturingServiceFeign manufacturingServiceFeign;
    @Value("${spring.application.name}")
    private String appName;

    @Autowired
    ManufacturingService manufacturingService;

    /** GetMapping for persisted configurations
     * @param id configuration id
     * @return JSON of ConfigDto with given id
     */
    @GetMapping(
            value = "/get/{id}",
            produces = "application/json"
    )
    public ConfigDto getConfigById(@PathVariable UUID id){
        return manufacturingService.getConfigById(id);
    }

    @PostMapping(
            value = "/save",
            consumes = "application/json",
            produces = "application/json"
    )
    public UUID saveConfig(@RequestBody ConfigDto dto){
        return manufacturingService.saveConfig(dto);
    }

    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @PostMapping(
            value = "/analyse",
            consumes = "application/json",
            produces = "application/json"
    )
    public ConfigDto analyseConfig(@RequestBody ConfigDto dto){

        return manufacturingService.analyse(dto);
    }

    @DeleteMapping(
            value = "/delete/{id}"
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCondigById(@PathVariable UUID id) {
        manufacturingService.deleteConfigById(id);
    }

    @DeleteMapping(
            value = "/delete/all"
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCondig() {
        manufacturingService.deleteConfig();
    }

}

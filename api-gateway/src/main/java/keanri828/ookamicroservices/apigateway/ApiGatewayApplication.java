package keanri828.ookamicroservices.apigateway;

import com.netflix.discovery.EurekaClient;
import keanri828.ookamicroservices.apigateway.model.ConfigDto;
import keanri828.ookamicroservices.apigateway.services.APIServiceFeign;
import keanri828.ookamicroservices.apigateway.services.PersistencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import java.util.List;
import java.util.UUID;

@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableFeignClients

public class ApiGatewayApplication {

    @Autowired
    private PersistencyService persistencyService;

    @Autowired
    private APIServiceFeign apiServiceFeign;

    @Qualifier("eurekaClient")
    @Autowired
    private EurekaClient eurekaClient;

    @Value("${spring.application.name}")
    private String appName;

    /** GetMapping for persisted configurations
     * @param id configuration id
     * @return JSON of ConfigDto with given id
     */
    @GetMapping(
            value = "/api/{id}",
            produces = "application/json"
    )
    public ConfigDto getConfigById(@PathVariable UUID id){
        return persistencyService.getConfigById(id);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(
            value = "/api/all",
            produces = "application/json"
    )
    public List<ConfigDto> getAllConfig(){
        return persistencyService.getAllConfig();
    }

    /** aktuell eigentlich nur zum Testen des Endpunkts. Die Konfiguration soll automatisch
     * gespeichert werden
     * @param dto
     * @return
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(
            value = "/api/save",
            consumes = "application/json",
            produces = "application/json"
    )
    public UUID saveConfig(@RequestBody @Valid ConfigDto dto){
        return persistencyService.saveConfig(dto);
    }

    //todo Testing of consuming rest of other ms by using eureka------

    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @PostMapping(
            value = "/api/analyse",
            consumes = "application/json",
            produces = "application/json"
    )
    public ConfigDto analyseConfig(@RequestBody @Valid ConfigDto dto){

        return persistencyService.analyse(dto);
    }



    //Tesing END can be removed if implemented

    @DeleteMapping(
            value = "/api/{id}"
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCondigById(@PathVariable UUID id) {
        persistencyService.deleteConfigById(id);
    }

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }


}

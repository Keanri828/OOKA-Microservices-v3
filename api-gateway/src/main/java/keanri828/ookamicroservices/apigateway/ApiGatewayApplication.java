package keanri828.ookamicroservices.apigateway;

import com.netflix.discovery.EurekaClient;
import keanri828.ookamicroservices.apigateway.model.AlgoStates;
import keanri828.ookamicroservices.apigateway.model.ConfigDto;
import keanri828.ookamicroservices.apigateway.serviceHandlers.APIServiceFeign;
import keanri828.ookamicroservices.apigateway.serviceHandlers.ServiceHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
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
    private ServiceHandler serviceHandler;

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
        return serviceHandler.getConfigById(id);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(
            value = "/api/all",
            produces = "application/json"
    )
    public List<ConfigDto> getAllConfig(){
        return serviceHandler.getAllConfig();
    }

    /** aktuell eigentlich nur zum Testen des Endpunkts. Die Konfiguration soll automatisch
     * gespeichert werden
     * @param dto
     * @return
     */
   /* @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(
            value = "/api/save",
            consumes = "application/json",
            produces = "application/json"
    )
    public UUID saveConfig(@RequestBody @Valid ConfigDto dto){
        return serviceHandler.saveConfig(dto);
    }*/

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

        return serviceHandler.analyse(dto);
    }

    @GetMapping(
            value = "/api/state",
            produces = "application/json"
    )
    public AlgoStates getStates() {
        return serviceHandler.getStates();
    }

    /*@GetMapping(
            value = "/api/results/{id}",
            produces = "application/json"
    )
    public ConfigDto fetchResults(@PathVariable UUID id) {
        return new ConfigDto(); // todo impl: NICHT CONFIGDTO, nur Ergebnisse
    }*/



    //Tesing END can be removed if implemented

    /*@DeleteMapping(
            value = "/api/{id}"
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCondigById(@PathVariable UUID id) {
        serviceHandler.deleteConfigById(id);
    }*/

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
    @GetMapping(
            value = "/api/retry/{id}",
            produces = "application/json"
    )
    public ConfigDto retryConfig(@PathVariable UUID id){

        return serviceHandler.retry(id);
    }

}

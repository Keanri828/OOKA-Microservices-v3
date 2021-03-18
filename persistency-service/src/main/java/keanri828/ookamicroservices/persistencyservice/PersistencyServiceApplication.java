package keanri828.ookamicroservices.persistencyservice;

import keanri828.ookamicroservices.persistencyservice.databaseconf.ConfigPersistencyService;
import keanri828.ookamicroservices.persistencyservice.databaseconf.PersitencyFeign;
import keanri828.ookamicroservices.persistencyservice.entities.ConfigEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@RestController
@EnableJpaRepositories
public class PersistencyServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersistencyServiceApplication.class, args);
    }

    @Autowired
    ConfigPersistencyService configPersistencyService;

    @Autowired
    PersitencyFeign persitencyFeign;

    @PostMapping(
            value = "/config/save",
            consumes = "application/json",
            produces = "application/json"
    )
    public UUID analyseConfig(@RequestBody ConfigEntity dto){

       return configPersistencyService.save(dto);
    }

    @GetMapping(
            value = "/config/get/{id}",
            produces = "application/json"
    )
    public ConfigEntity getConfigById(@PathVariable UUID id){
        return configPersistencyService.findById(id);
    }


    @GetMapping(
            value = "/config/get/all",
            produces = "application/json"
    )
    public List<ConfigEntity> getAllConfig(){
        return configPersistencyService.findAll();
    }

    @DeleteMapping(
            value = "/config/delete/{id}"
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCondigById(@PathVariable UUID id) {
        configPersistencyService.deleteById(id);
    }

}

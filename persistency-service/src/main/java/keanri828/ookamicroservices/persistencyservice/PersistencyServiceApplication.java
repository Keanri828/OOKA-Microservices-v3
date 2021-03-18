package keanri828.ookamicroservices.persistencyservice;

import com.netflix.discovery.EurekaClient;
import keanri828.ookamicroservices.persistencyservice.databaseconf.ConfigService;
import keanri828.ookamicroservices.persistencyservice.databaseconf.PersitencyFeign;
import keanri828.ookamicroservices.persistencyservice.entities.ConfigEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    ConfigService configService;

    @Autowired
    PersitencyFeign persitencyFeign;

    @PostMapping(
            value = "/config/save",
            consumes = "application/json"
    )
    public void analyseConfig(@RequestBody ConfigEntity dto){

        configService.save(dto);
    }

    @GetMapping(
            value = "/config/get/{id}",
            produces = "application/json"
    )
    public ConfigEntity getConfigById(@PathVariable UUID id){
        return configService.findById(id);
    }


    @GetMapping(
            value = "/config/all",
            produces = "application/json"
    )
    public List<ConfigEntity> getAllConfig(){
        return configService.findAll();
    }

    @DeleteMapping(
            value = "/config/{id}"
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCondigById(@PathVariable UUID id) {
        configService.deleteById(id);
    }

}

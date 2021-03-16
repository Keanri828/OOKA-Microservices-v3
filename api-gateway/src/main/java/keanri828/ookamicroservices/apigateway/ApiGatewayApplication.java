package keanri828.ookamicroservices.apigateway;

import com.netflix.discovery.EurekaClient;
import keanri828.ookamicroservices.apigateway.model.ConfigDto;
import keanri828.ookamicroservices.apigateway.services.PersistencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@SpringBootApplication
@RestController
public class ApiGatewayApplication {

    @Autowired
    private PersistencyService persistencyService;

    @Autowired
    private EurekaClient eurekaClient;

    @Value("${spring.application.name}")
    private String appName;

    /** GetMapping for persisted configurations
     * @param id configuration id
     * @return JSON of ConfigDto with given id
     */
    @GetMapping(
            value = "/{id}",
            produces = "application/json"
    )
    public ConfigDto getConfigById(@PathVariable UUID id){
        return persistencyService.getConfigById(id);
    }

    /** aktuell eigentlich nur zum Testen des Endpunkts. Die Konfiguration soll automatisch
     * gespeichert werden
     * @param dto
     * @return
     */
    @PostMapping(
            consumes = "application/json",
            produces = "application/json"
    )
    public UUID saveConfig(@RequestBody @Valid ConfigDto dto){
        return persistencyService.saveConfig(dto);
    }

    @DeleteMapping(
            value = "/{id}"
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCondigById(@PathVariable UUID id) {
        persistencyService.deleteConfigById(id);
    }

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

}

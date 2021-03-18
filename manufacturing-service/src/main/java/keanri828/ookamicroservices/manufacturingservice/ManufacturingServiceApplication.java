package keanri828.ookamicroservices.manufacturingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@FeignClient("manufacturing-service")
public class ManufacturingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManufacturingServiceApplication.class, args);
    }

}

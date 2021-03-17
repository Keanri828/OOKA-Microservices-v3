package keanri828.ookamicroservices.manufacturingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ManufacturingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManufacturingServiceApplication.class, args);
    }

}

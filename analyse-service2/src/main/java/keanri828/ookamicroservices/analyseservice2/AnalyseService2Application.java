package keanri828.ookamicroservices.analyseservice2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
//@FeignClient("analyse-service2")
public class AnalyseService2Application {

    public static void main(String[] args) {
        SpringApplication.run(AnalyseService2Application.class, args);
    }

}

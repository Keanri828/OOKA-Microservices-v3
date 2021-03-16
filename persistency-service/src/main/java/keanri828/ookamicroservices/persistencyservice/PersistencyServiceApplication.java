package keanri828.ookamicroservices.persistencyservice;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PersistencyServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersistencyServiceApplication.class, args);
    }

}

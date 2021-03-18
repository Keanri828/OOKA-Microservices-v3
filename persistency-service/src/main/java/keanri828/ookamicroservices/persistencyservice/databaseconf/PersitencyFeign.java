package keanri828.ookamicroservices.persistencyservice.databaseconf;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("persitency-service")
public interface PersitencyFeign {
}

package keanri828.ookamicroservices.manufacturingservice.services;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("manufacturing-service")
public interface ManufacturingServiceFeign {
}

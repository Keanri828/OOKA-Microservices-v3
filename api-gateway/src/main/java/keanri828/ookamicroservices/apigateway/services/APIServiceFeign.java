package keanri828.ookamicroservices.apigateway.services;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("api-service")
public interface APIServiceFeign {
}

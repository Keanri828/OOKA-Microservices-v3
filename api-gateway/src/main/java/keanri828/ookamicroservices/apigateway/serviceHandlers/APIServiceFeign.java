package keanri828.ookamicroservices.apigateway.serviceHandlers;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("api-service")
public interface APIServiceFeign {
}

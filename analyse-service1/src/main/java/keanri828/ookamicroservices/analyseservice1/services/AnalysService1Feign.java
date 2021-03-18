package keanri828.ookamicroservices.analyseservice1.services;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("analyse-service1")
public interface AnalysService1Feign {
}

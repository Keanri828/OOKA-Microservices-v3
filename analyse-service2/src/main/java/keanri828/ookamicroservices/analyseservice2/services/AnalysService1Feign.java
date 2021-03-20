package keanri828.ookamicroservices.analyseservice2.services;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("analyse-service2")
public interface AnalysService1Feign {
}

package keanri828.ookamicroservices.apigateway.serviceHandlers;

import keanri828.ookamicroservices.apigateway.model.AlgoStates;
import keanri828.ookamicroservices.apigateway.model.AnalyseStatus;
import keanri828.ookamicroservices.apigateway.model.ConfigDto;
import keanri828.ookamicroservices.apigateway.model.EngineTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.ObjectInputFilter;
import java.util.*;

@Service
public class ServiceHandlerImpl implements ServiceHandler {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public UUID saveConfig(ConfigDto configDto) {
        // todo send request to Microservice
        return UUID.randomUUID();
    }

    @Override
    public ConfigDto getConfigById(UUID id) {
        // todo send request to Microservice
        return ConfigDto.builder()
                .id(id)
                .divValveDuplFilter(true)
                .engineType(EngineTypeEnum.V10)
                .build();
    }

    @Override
    public List<ConfigDto> getAllConfig(){
        // todo no real implementation, just a data mockup
        List<ConfigDto> res;
        ConfigDto[] dto = restTemplate.getForObject("http://persistency-service/config/get/all/", ConfigDto[].class);
        res = Arrays.asList(dto);
        return res;
    }

    //Test analyse
    @Override
    public ConfigDto analyse(ConfigDto dto){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ConfigDto> httpEntity = new HttpEntity<>(dto, headers);
        //System.out.println(httpEntity.getBody().toString());
        Boolean[] res;

        //res = restTemplate.postForObject("http://localhost:8081/analyse1/", httpEntity, Boolean.class);
        res = restTemplate.postForObject("http://analyse-service1/analyse1/", httpEntity, Boolean[].class);
        List<Boolean> result = Arrays.asList(res);

        dto.setSuccessful1(result.get(0));
        dto.setSuccessful2(result.get(1));
        return dto;
    }

    // get current states of analyse-services
    @Override
    public AlgoStates getStates() {

        AnalyseStatus state1 = restTemplate.getForObject("http://analyse-service1/actuator/health/working", AnalyseStatus.class);
        AnalyseStatus state2 = restTemplate.getForObject("http://analyse-service1/actuator/health/working", AnalyseStatus.class);
        //String state2 = restTemplate.getForObject("http://analyse-service2/state", String.class);
        return AlgoStates.builder().state1(state1.getStatus()).state2(state2.getStatus()).build();
    }

    @Override
    public void deleteConfigById(UUID id) {
        // todo send request to Microservice
    }
}

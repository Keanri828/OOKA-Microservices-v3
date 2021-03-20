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
    public UUID saveConfig(ConfigDto dto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ConfigDto> httpEntity = new HttpEntity<>(dto, headers);
        return restTemplate.postForObject("http://manufacturing-service/save/", httpEntity, UUID.class);
    }

    @Override
    public ConfigDto getConfigById(UUID id) {
        return restTemplate.getForObject("http://manufacturing-service/get/"+id.toString(), ConfigDto.class);
    }

    @Override
    public List<ConfigDto> getAllConfig(){
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


        //res = restTemplate.postForObject("http://localhost:8081/analyse1/", httpEntity, Boolean.class);
        dto = restTemplate.postForObject("http://manufacturing-service/analyse/", httpEntity, ConfigDto.class);

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
        restTemplate.delete("http://manufacturing-service/delete/all");
    }

    @Override
    public ConfigDto retry(UUID id){
        ConfigDto dto = getConfigById(id);
        dto = analyse(dto);
        return dto;
    }
}

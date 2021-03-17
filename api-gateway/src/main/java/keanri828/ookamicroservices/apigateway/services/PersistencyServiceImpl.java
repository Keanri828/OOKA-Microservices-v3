package keanri828.ookamicroservices.apigateway.services;

import keanri828.ookamicroservices.apigateway.model.ConfigDto;
import keanri828.ookamicroservices.apigateway.model.EngineTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.ObjectInputFilter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PersistencyServiceImpl implements PersistencyService {
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
        List<ConfigDto> res= new ArrayList<>();
        for (int i = 0; i<4;i++) {
            res.add(ConfigDto.builder()
                    .id(UUID.randomUUID())
                    .divValveDuplFilter(true)
                    .divValveFuelFilter(true)
                    .oilReplSystem(false)
                    .duplFuelFilter(false)
                    .fuelLeakageMonitor(true)
                    .successful1(true)
                    .successful2(false)
                    .engineType(EngineTypeEnum.V10)
                    .timestamp(new Date())
                    .build());
        }
        return res;
    }

    //Test analyse
    @Autowired
    RestTemplate restTemplate;
    @Override
    public ConfigDto analyse(ConfigDto dto){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ConfigDto> httpEntity = new HttpEntity<>(dto, headers);

        Boolean res = restTemplate.postForObject("http://localhost:8081/analyse1/",httpEntity,Boolean.class);
        dto.setSuccessful1(res);
        return dto;
    }

    @Override
    public void deleteConfigById(UUID id) {
        // todo send request to Microservice
    }
}

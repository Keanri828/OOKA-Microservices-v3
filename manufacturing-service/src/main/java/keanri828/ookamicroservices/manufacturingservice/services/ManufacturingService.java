package keanri828.ookamicroservices.manufacturingservice.services;

import keanri828.ookamicroservices.manufacturingservice.model.ConfigDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class ManufacturingService {
    @Autowired
    RestTemplate restTemplate;

    public ConfigDto analyse(ConfigDto dto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ConfigDto> httpEntity = new HttpEntity<>(dto, headers);
        //System.out.println(httpEntity.getBody().toString());
        Boolean[] res;
        try {
            //res = restTemplate.postForObject("http://localhost:8081/analyse1/", httpEntity, Boolean.class);
            res = restTemplate.postForObject("http://analyse-service1/analyse1/", httpEntity, Boolean[].class);

        }catch (Exception ex){
            System.out.println("-----------------------------------------fehler in analyse");
            res = new Boolean[]{false, false};
        }
        List<Boolean> result = Arrays.asList(res);

        dto.setSuccessful1(result.get(0));
        dto.setSuccessful2(result.get(1));
        dto.setId(saveConfig(dto));
        return dto;
    }

    public void deleteConfig() {
        restTemplate.delete("http://persistency-service/config/delete/all");
    }

    public void deleteConfigById(UUID id) {
        restTemplate.delete("/config/delete/" + id.toString());
    }

    public ConfigDto getConfigById(UUID id) {
        return restTemplate.getForObject("http://persistency-service/config/get/" + id.toString(), ConfigDto.class);
    }

    public UUID saveConfig(ConfigDto dto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ConfigDto> httpEntity = new HttpEntity<>(dto, headers);
        return restTemplate.postForObject("http://persistency-service/config/save/", httpEntity, UUID.class);
    }
}

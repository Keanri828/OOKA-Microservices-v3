package keanri828.ookamicroservices.analyseservice1.services;

import keanri828.ookamicroservices.analyseservice1.model.ConfigDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class AnalyseService {
    public String state = "up";

    @Autowired
    RestTemplate restTemplate;

    public List<Boolean> analyseConfig(ConfigDto dto){
        state = "running";
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        List<Boolean> erg = new ArrayList();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<ConfigDto> httpEntity = new HttpEntity<>(dto, headers);
            Boolean res = restTemplate.postForObject("http://analyse-service2/analyse1/", httpEntity, Boolean.class);
            erg.add(dto.getFuelLeakageMonitor() && dto.getDivValveFuelFilter());
            erg.add(res);
        }catch(Exception ex){
            state="error";
            erg.add(dto.getFuelLeakageMonitor() && dto.getDivValveFuelFilter());
            erg.add(null);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        state = "up";
        return erg;
    }

    public String getState(){
        return state;
    }
}

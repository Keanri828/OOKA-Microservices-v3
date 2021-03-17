package keanri828.ookamicroservices.analyseservice1.services;

import keanri828.ookamicroservices.analyseservice1.model.ConfigDto;
import org.springframework.stereotype.Service;

@Service
public class AnalyseService {

    public Boolean analyseConfig(ConfigDto dto){
        /*try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }*/
        return dto.getFuelLeakageMonitor() && dto.getDivValveFuelFilter();
    }
}

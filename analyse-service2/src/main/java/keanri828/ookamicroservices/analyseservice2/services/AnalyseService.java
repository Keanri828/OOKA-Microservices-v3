package keanri828.ookamicroservices.analyseservice2.services;

import keanri828.ookamicroservices.analyseservice2.model.ConfigDto;
import org.springframework.stereotype.Service;

@Service
public class AnalyseService {
    public String state = "up";
    public Boolean analyseConfig(ConfigDto dto){
        state = "running";
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        state = "up";
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return dto.getFuelLeakageMonitor() && dto.getDivValveFuelFilter();
    }

    public String getState(){
        return state;
    }
}

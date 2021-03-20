package keanri828.ookamicroservices.analyseservice1.model;

import keanri828.ookamicroservices.analyseservice1.services.AnalyseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

@Component
public class WorkingHealthIndicator extends AbstractHealthIndicator {

    @Autowired
    AnalyseService analyseService;
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        // Use the builder to build the health status details that should be reported.
        // If you throw an exception, the status will be DOWN with the exception message.
        builder.status("UP");
        if(analyseService.getState().equals("up")){
            builder.status("UP");
        }
        if(analyseService.getState().equals("running")){
            builder.status("WORKING");
        }
        if(analyseService.getState().equals("error")){
            builder.status("ERROR");
        }
    }
}
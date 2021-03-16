package keanri828.ookamicroservices.apigateway.services;

import keanri828.ookamicroservices.apigateway.model.ConfigDto;
import keanri828.ookamicroservices.apigateway.model.EngineTypeEnum;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
                    .engineType(EngineTypeEnum.V10)
                    .build());
        }
        return res;
    }

    @Override
    public void deleteConfigById(UUID id) {
        // todo send request to Microservice
    }
}

package keanri828.ookamicroservices.apigateway.services;

import keanri828.ookamicroservices.apigateway.model.ConfigDto;

import java.util.List;
import java.util.UUID;

public interface PersistencyService {
    public UUID saveConfig(ConfigDto configDto);
    public ConfigDto getConfigById(UUID id);
    public void deleteConfigById(UUID id);
    public List<ConfigDto> getAllConfig();
    public ConfigDto analyse(ConfigDto dto);
}

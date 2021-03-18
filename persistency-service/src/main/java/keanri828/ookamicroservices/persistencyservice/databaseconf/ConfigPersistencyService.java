package keanri828.ookamicroservices.persistencyservice.databaseconf;

import com.google.common.collect.Lists;
import keanri828.ookamicroservices.persistencyservice.entities.ConfigEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ConfigPersistencyService {

    @Autowired
    ConfigRepository configRepository;

    public List<ConfigEntity> findAll() {
        return Lists.newArrayList(configRepository.findAll());
    }

    public long count() {
        return configRepository.count();
    }

    public void delete(ConfigEntity configEntity) {
        configRepository.delete(configEntity);
    }

    public UUID save(ConfigEntity configEntity) {
        return configRepository.save(configEntity).getId();
    }
    public void deleteById(UUID id) {
        configRepository.deleteById(id);
    }
    public void deleteAll(){
        configRepository.deleteAll();
    }
    public ConfigEntity findById(UUID id){
        return configRepository.findById(id).orElse(null);
    }
}

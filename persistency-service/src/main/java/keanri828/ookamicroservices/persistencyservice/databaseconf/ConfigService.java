package keanri828.ookamicroservices.persistencyservice.databaseconf;

import keanri828.ookamicroservices.persistencyservice.entities.ConfigEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfigService {

    @Autowired
    ConfigRepository configRepository;

    public List<ConfigEntity> findAll() {
        return configRepository.findAll();
    }

    public long count() {
        return configRepository.count();
    }

    public void delete(ConfigEntity configEntity) {
        configRepository.delete(configEntity);
    }

    public void save(ConfigEntity configEntity) {

        configRepository.save(configEntity);
    }
}

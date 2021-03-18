package keanri828.ookamicroservices.persistencyservice.databaseconf;

import keanri828.ookamicroservices.persistencyservice.entities.ConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.UUID;

public interface ConfigRepository extends JpaRepository<ConfigEntity, UUID>{
}

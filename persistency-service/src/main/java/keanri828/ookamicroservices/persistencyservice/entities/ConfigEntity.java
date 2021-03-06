package keanri828.ookamicroservices.persistencyservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfigEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(updatable = false, nullable = false)
    private UUID id;
    //private String test;
    private String engineType;

    private Boolean oilReplSystem;

    private Boolean divValveDuplFilter;

    private Boolean duplFuelFilter;

    private Boolean divValveFuelFilter;

    private Boolean fuelLeakageMonitor;

    //@CreationTimestamp
    @UpdateTimestamp
    //@Column(updatable = false)
    private Date timestamp; // for sorting in history

    private Boolean successful1;

    private Boolean successful2;
}

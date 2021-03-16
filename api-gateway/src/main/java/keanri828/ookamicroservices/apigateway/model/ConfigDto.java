package keanri828.ookamicroservices.apigateway.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfigDto {
    @Null
    private UUID id;

    @NotNull
    private EngineTypeEnum engineType;

    // Optional Equipment
    @NotNull
    private Boolean oilReplSystem;

    @NotNull
    private Boolean divValveDuplFilter;

    @NotNull
    private Boolean duplFuelFilter;

    @NotNull
    private Boolean divValveFuelFilter;

    @NotNull
    private Boolean fuelLeakageMonitor;

    @Null
    private Date timestamp;
}

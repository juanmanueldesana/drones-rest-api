package com.dronesserviceapi.models.responses;

import java.time.LocalDateTime;
import java.util.List;

import com.dronesserviceapi.entities.DroneEntity;

import lombok.Data;

@Data
public class AvailableDroneRest {

    private LocalDateTime timestamp;
    private List<DroneEntity> drones;

    public AvailableDroneRest(String status, LocalDateTime timestamp, List<DroneEntity> drones) {
        this.timestamp = timestamp;
        this.drones = drones;
    }

}

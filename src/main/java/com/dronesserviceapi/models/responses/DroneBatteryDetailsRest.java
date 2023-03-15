package com.dronesserviceapi.models.responses;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DroneBatteryDetailsRest {
    
    private String serialNumber;
    private String status;
    private String battery;
    private LocalDateTime timestamp;

}

package com.dronesserviceapi.models.responses;

import java.time.LocalDateTime;

import com.dronesserviceapi.entities.MedicationEntity;

import lombok.Data;

@Data
public class DroneMedicationLoadRest {

    private String serialNumber;
    private String result;
    private LocalDateTime timestamp;
    private MedicationEntity medication;

}

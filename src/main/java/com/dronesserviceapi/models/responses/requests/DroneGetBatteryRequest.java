package com.dronesserviceapi.models.responses.requests;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DroneGetBatteryRequest {

    @NotBlank(message = "Serial number cannot be blank")
    private String serialNumber;

}

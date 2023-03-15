package com.dronesserviceapi.models.responses.requests;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class DroneDeliveryRequest {

    @NotBlank(message = "Serial number cannot be blank")
    private String serialNumber;

}

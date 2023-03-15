package com.dronesserviceapi.models.responses.requests;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DroneRegisterRequest {

    @NotBlank(message = "Serial number cannot be blank")
    private String serialNumber;

    @NotBlank(message = "Model cannot be blank")
    private String model;

    @NotNull(message = "Weight limit cannot be null")
    private Double weightLimit;

    @NotNull(message = "Battery cannot be null")
    private BigDecimal battery;

    @NotBlank(message = "State cannot be blank")
    private String state;

}

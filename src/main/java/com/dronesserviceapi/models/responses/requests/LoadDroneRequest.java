package com.dronesserviceapi.models.responses.requests;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoadDroneRequest {

    @NotBlank(message = "Serial number cannot be blank")
    private String serialNumber;

    @NotBlank(message = "Source cannot be blank")
    private String source;

    @NotBlank(message = "Destination cannot be blank")
    private String destination;

    @NotBlank(message = "Code cannot be blank")
    private String code;

}

package com.dronesserviceapi.models.responses;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MessageRest {
    
    private String result;
    private String message;
    private LocalDateTime timestamp;

}

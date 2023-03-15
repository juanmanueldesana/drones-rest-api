package com.dronesserviceapi.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.dronesserviceapi.enums.Model;
import com.dronesserviceapi.enums.State;

import lombok.Data;

@Entity(name = "drones")
@Data
public class DroneEntity {

    @Id
    @Column(name = "serial_number", columnDefinition = "VARCHAR(16) NOT NULL")
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    private Model model;

    @Column(name = "weight_limit", columnDefinition = "DECIMAL(10,2) NOT NULL")
    private Double weightLimit;

    @Column(name = "battery", precision = 3, scale = 2)
    private BigDecimal battery;

    @Enumerated(EnumType.STRING)
    private State state;

}

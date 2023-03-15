package com.dronesserviceapi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "medications")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicationEntity {

    @Id
    @Column(name = "code", columnDefinition = "VARCHAR(16) NOT NULL")
    private String code;

    @Column(name = "name", columnDefinition = "VARCHAR(30) NOT NULL")
    private String name;

    @Column(name = "weight", columnDefinition = "DECIMAL(10,2) NOT NULL")
    private Double weight;

    @Column(name = "image")
    private String image;

}

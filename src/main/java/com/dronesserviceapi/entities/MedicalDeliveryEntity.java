package com.dronesserviceapi.entities;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity(name = "medical_deliveries")
@Data
public class MedicalDeliveryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "delivery_time", columnDefinition = "DATE NOT NULL")
    private LocalDateTime deliveryTime;

    @OneToOne(targetEntity = LoadMedicationEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_tracking_number", referencedColumnName = "tracking_number")
    private LoadMedicationEntity loadMedication;

}

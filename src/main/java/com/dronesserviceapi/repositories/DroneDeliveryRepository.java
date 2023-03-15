package com.dronesserviceapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dronesserviceapi.entities.MedicalDeliveryEntity;

@Repository
public interface DroneDeliveryRepository extends JpaRepository<MedicalDeliveryEntity, String> {

}

package com.dronesserviceapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dronesserviceapi.entities.MedicationEntity;

@Repository
public interface MedicationRepository extends JpaRepository<MedicationEntity, String> {

    MedicationEntity findByCode(String code);

}

package com.dronesserviceapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dronesserviceapi.entities.LoadMedicationEntity;

@Repository
public interface LoadDroneRepository extends JpaRepository<LoadMedicationEntity, String> {

    @Query(value = "SELECT * from load_medications e where e.fk_serial_number =:serialno ", nativeQuery = true)
    LoadMedicationEntity findByDrone(@Param("serialno") String serialno);

    @Query(value = "SELECT e from load_medications e where e.medication.code =:code ")
    LoadMedicationEntity findByCode(@Param("code") String code);

}

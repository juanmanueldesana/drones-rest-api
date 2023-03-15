package com.dronesserviceapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dronesserviceapi.entities.DroneEntity;

@Repository
public interface DroneRepository extends JpaRepository<DroneEntity, String> {

    DroneEntity findBySerialNumber(String serialNumber);

    List<DroneEntity> findAllByState(String state);

    @Modifying
    @Query(value = "UPDATE drone d SET d.state = :state WHERE d.serial_number = :serialNumber", nativeQuery = true)
    void setUpdateState(@Param("state") String state, @Param("serialNumber") String serialNumber);

}

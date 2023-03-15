package com.dronesserviceapi.services;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dronesserviceapi.entities.DroneEntity;
import com.dronesserviceapi.entities.LoadMedicationEntity;
import com.dronesserviceapi.entities.MedicalDeliveryEntity;
import com.dronesserviceapi.entities.MedicationEntity;
import com.dronesserviceapi.enums.Model;
import com.dronesserviceapi.enums.State;
import com.dronesserviceapi.interfaces.DroneInterface;
import com.dronesserviceapi.models.responses.AvailableDroneRest;
import com.dronesserviceapi.models.responses.DeliverDroneRest;
import com.dronesserviceapi.models.responses.DroneBatteryDetailsRest;
import com.dronesserviceapi.models.responses.DroneMedicationLoadRest;
import com.dronesserviceapi.models.responses.LoadDroneRest;
import com.dronesserviceapi.models.responses.RegisterDroneRest;
import com.dronesserviceapi.models.responses.requests.DroneDeliveryRequest;
import com.dronesserviceapi.models.responses.requests.DroneGetBatteryRequest;
import com.dronesserviceapi.models.responses.requests.DroneRegisterRequest;
import com.dronesserviceapi.models.responses.requests.LoadDroneRequest;
import com.dronesserviceapi.repositories.DroneDeliveryRepository;
import com.dronesserviceapi.repositories.DroneRepository;
import com.dronesserviceapi.repositories.LoadDroneRepository;
import com.dronesserviceapi.repositories.MedicationRepository;

@Service
public class DroneService implements DroneInterface {

    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private MedicationRepository medicationRepository;

    @Autowired
    private LoadDroneRepository loadDroneRepository;

    @Autowired
    private DroneDeliveryRepository droneDeliveryRepository;

    @Override
    public RegisterDroneRest registerDrone(DroneRegisterRequest drone) {

        DroneEntity droneEntity = new DroneEntity();
        droneEntity.setSerialNumber(drone.getSerialNumber());

        switch (drone.getModel()) {
            case "LIGHTWEIGHT":
                droneEntity.setModel(Model.LIGHTWEIGHT);
                break;
            case "MIDDLEWEIGHT":
                droneEntity.setModel(Model.MIDDLEWEIGHT);
                break;

            case "CRUISERWEIGHT":
                droneEntity.setModel(Model.CRUISERWEIGHT);
                break;

            case "HEAVYWEIGHT":
                droneEntity.setModel(Model.HEAVYWEIGHT);
                break;
        }

        droneEntity.setWeightLimit(drone.getWeightLimit());
        droneEntity.setBattery(drone.getBattery());

        switch (drone.getState()) {
            case "IDLE":
                droneEntity.setState(State.IDLE);
                break;
            case "LOADING":
                droneEntity.setState(State.LOADING);
                break;
            case "LOADED":
                droneEntity.setState(State.LOADED);
                break;
            case "DELIVERING":
                droneEntity.setState(State.DELIVERING);
                break;
            case "DELIVERED":
                droneEntity.setState(State.DELIVERED);
                break;
            case "RETURNING":
                droneEntity.setState(State.RETURNING);
                break;
        }

        droneRepository.save(droneEntity);

        RegisterDroneRest registerDroneRest = new RegisterDroneRest();
        registerDroneRest.setSerialNumber(droneEntity.getSerialNumber());
        registerDroneRest.setResult("success");
        registerDroneRest.setMessage("Drone registered successfully");
        registerDroneRest.setTimestamp(LocalDateTime.now());

        return registerDroneRest;
    }

    @Override
    public DroneBatteryDetailsRest getBatteryLevel(DroneGetBatteryRequest drone) {

        DecimalFormat df = new DecimalFormat("#%");
        DroneEntity droneEntity = droneRepository.findBySerialNumber(drone.getSerialNumber());
        if (droneEntity == null) {
            throw new RuntimeException("Drone not found");
        }

        DroneBatteryDetailsRest droneBatteryDetailsRest = new DroneBatteryDetailsRest();
        droneBatteryDetailsRest.setSerialNumber(droneEntity.getSerialNumber());
        droneBatteryDetailsRest.setStatus("success");
        droneBatteryDetailsRest.setBattery(df.format(droneEntity.getBattery()));
        droneBatteryDetailsRest.setTimestamp(LocalDateTime.now());

        return droneBatteryDetailsRest;
    }

    @Override
    public DroneMedicationLoadRest getMedicationLoad(String serialNumber) {

        LoadMedicationEntity loadMedicationEntity = loadDroneRepository.findByDrone(serialNumber);

        if (loadMedicationEntity == null) {
            throw new RuntimeException("No medication loaded on drone");
        }

        DroneMedicationLoadRest droneMedicationLoadRest = new DroneMedicationLoadRest();
        droneMedicationLoadRest.setSerialNumber(serialNumber);
        droneMedicationLoadRest.setMedication(loadMedicationEntity.getMedication());
        droneMedicationLoadRest.setTimestamp(LocalDateTime.now());
        droneMedicationLoadRest.setResult("success");

        return droneMedicationLoadRest;

    }

    @Override
    public AvailableDroneRest getAvailableDrones() {

        List<DroneEntity> availableDrones = droneRepository.findAllByState(State.IDLE.toString());
        return new AvailableDroneRest(State.IDLE.toString(), LocalDateTime.now(), availableDrones);

    }

    @Override
    public LoadDroneRest loadDrone(LoadDroneRequest loadDroneRequest) {

        MedicationEntity medication1 = new MedicationEntity("WE232344", "Covax", 100.00, "sade23Rd");
        MedicationEntity medication2 = new MedicationEntity("WE232345", "Meloxicam", 150.00, "sade2Y4d");
        MedicationEntity medication3 = new MedicationEntity("WE232346", "Metformin", 200.00, "sade2U4d");
        MedicationEntity medication4 = new MedicationEntity("WE232347", "Acetaminophen", 300.00, "sade2Q4d");
        MedicationEntity medication5 = new MedicationEntity("WE232348", "Amoxicillin", 400.00, "sa3e234d");
        MedicationEntity medication6 = new MedicationEntity("WE232349", "Ativan", 260.00, "sade237d");
        MedicationEntity medication7 = new MedicationEntity("WE2323510", "Atorvastatin", 180.00, "sade2F4d");
        MedicationEntity medication8 = new MedicationEntity("WE2323511", "Azithromycin", 400.00, "sade2B4d");
        MedicationEntity medication9 = new MedicationEntity("WE2323512", "Zyloprim", 400.00, "sadeH34d");
        MedicationEntity medication10 = new MedicationEntity("WE2323513", "Diprolene ", 400.00, "sade234J");
        medicationRepository.save(medication1);
        medicationRepository.save(medication2);
        medicationRepository.save(medication3);
        medicationRepository.save(medication4);
        medicationRepository.save(medication5);
        medicationRepository.save(medication6);
        medicationRepository.save(medication7);
        medicationRepository.save(medication8);
        medicationRepository.save(medication9);
        medicationRepository.save(medication10);

        droneRepository.setUpdateState(State.LOADING.toString(), loadDroneRequest.getSerialNumber());
        DroneEntity drone = droneRepository.findBySerialNumber(loadDroneRequest.getSerialNumber());
        MedicationEntity medication = medicationRepository.findByCode(loadDroneRequest.getCode());
        LoadMedicationEntity checkLoad = loadDroneRepository.findByCode(loadDroneRequest.getCode());

        if (checkLoad != null) {
            throw new RuntimeException("Medication code has already been loaded, try another code");
        }

        if (drone == null) {
            throw new RuntimeException("Drone specified does not exist");
        }

        if (medication == null) {
            throw new RuntimeException("Medication specified does not exist");
        }

        if (drone.getWeightLimit() < medication.getWeight()) {
            throw new RuntimeException("Medication weight exceeds drone weight limit");
        }

        if (drone.getBattery().compareTo(new BigDecimal(0.25)) < 0) {
            throw new RuntimeException("Drone battery is too low to load");
        }

        LoadMedicationEntity loadMedication = new LoadMedicationEntity();
        loadMedication.setDrone(drone);
        loadMedication.setMedication(medication);
        loadMedication.setSource(loadDroneRequest.getSource());
        loadMedication.setDestination(loadDroneRequest.getDestination());
        loadMedication.setCreationDate(LocalDateTime.now());
        loadDroneRepository.save(loadMedication);
        droneRepository.setUpdateState(State.LOADED.toString(), loadDroneRequest.getSerialNumber());

        LoadDroneRest loadDroneRest = new LoadDroneRest();
        loadDroneRest.setResult("success");
        loadDroneRest.setSerialNumber(loadDroneRequest.getSerialNumber());
        loadDroneRest.setMessage("Drone loaded successfully");
        loadDroneRest.setTimestamp(LocalDateTime.now());

        return loadDroneRest;

    }

    @Override
    public DeliverDroneRest deliverLoad(DroneDeliveryRequest droneDeliveryRequest) {

        droneRepository.setUpdateState(State.DELIVERING.toString(), droneDeliveryRequest.getSerialNumber());
        LoadMedicationEntity loadMedication = loadDroneRepository.findByDrone(droneDeliveryRequest.getSerialNumber());

        if (loadMedication == null) {
            throw new RuntimeException("Drone specified is not loaded or does not exist");
        }

        MedicalDeliveryEntity medicalDelivery = new MedicalDeliveryEntity();
        medicalDelivery.setLoadMedication(loadMedication);
        medicalDelivery.setDeliveryTime(LocalDateTime.now());
        droneDeliveryRepository.save(medicalDelivery);
        droneRepository.setUpdateState(State.DELIVERED.toString(), droneDeliveryRequest.getSerialNumber());

        DeliverDroneRest deliverDroneRest = new DeliverDroneRest();
        deliverDroneRest.setResult("success");
        deliverDroneRest.setSerialNumber(droneDeliveryRequest.getSerialNumber());
        deliverDroneRest.setMessage("Delivery completed successfully");
        deliverDroneRest.setTimestamp(LocalDateTime.now());

        return deliverDroneRest;

    }

    @Override
    public void preLoadData() {
        MedicationEntity medication1 = new MedicationEntity("WE232344", "Covax", 100.00, "sade23Rd");
        MedicationEntity medication2 = new MedicationEntity("WE232345", "Meloxicam", 150.00, "sade2Y4d");
        MedicationEntity medication3 = new MedicationEntity("WE232346", "Metformin", 200.00, "sade2U4d");
        MedicationEntity medication4 = new MedicationEntity("WE232347", "Acetaminophen", 300.00, "sade2Q4d");
        MedicationEntity medication5 = new MedicationEntity("WE232348", "Amoxicillin", 400.00, "sa3e234d");
        MedicationEntity medication6 = new MedicationEntity("WE232349", "Ativan", 260.00, "sade237d");
        MedicationEntity medication7 = new MedicationEntity("WE2323510", "Atorvastatin", 180.00, "sade2F4d");
        MedicationEntity medication8 = new MedicationEntity("WE2323511", "Azithromycin", 400.00, "sade2B4d");
        MedicationEntity medication9 = new MedicationEntity("WE2323512", "Zyloprim", 400.00, "sadeH34d");
        MedicationEntity medication10 = new MedicationEntity("WE2323513", "Diprolene ", 400.00, "sade234J");
        medicationRepository.save(medication1);
        medicationRepository.save(medication2);
        medicationRepository.save(medication3);
        medicationRepository.save(medication4);
        medicationRepository.save(medication5);
        medicationRepository.save(medication6);
        medicationRepository.save(medication7);
        medicationRepository.save(medication8);
        medicationRepository.save(medication9);
        medicationRepository.save(medication10);
    }

}

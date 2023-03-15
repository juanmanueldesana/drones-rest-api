package com.dronesserviceapi.controllers;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dronesserviceapi.interfaces.DroneInterface;
import com.dronesserviceapi.models.requests.DroneRegisterRequest;
import com.dronesserviceapi.models.requests.LoadDroneRequest;
import com.dronesserviceapi.models.responses.AvailableDroneRest;
import com.dronesserviceapi.models.responses.DeliverDroneRest;
import com.dronesserviceapi.models.responses.DroneBatteryDetailsRest;
import com.dronesserviceapi.models.responses.DroneMedicationLoadRest;
import com.dronesserviceapi.models.responses.LoadDroneRest;
import com.dronesserviceapi.models.responses.RegisterDroneRest;

@RestController
@RequestMapping("/api/drones")
@Validated
public class DroneController {

    @Autowired
    private DroneInterface droneService;

    @PostMapping("/register")
    public ResponseEntity<RegisterDroneRest> registerDrone(
            @Valid @NotNull @RequestBody DroneRegisterRequest droneRequest) {
        RegisterDroneRest newDrone = droneService.registerDrone(droneRequest);
        return new ResponseEntity<RegisterDroneRest>(newDrone, HttpStatus.CREATED);
    }

    @GetMapping("/available")
    public ResponseEntity<AvailableDroneRest> getAvailableDrones() {
        AvailableDroneRest availableDrones = droneService.getAvailableDrones();
        return new ResponseEntity<AvailableDroneRest>(availableDrones, HttpStatus.OK);
    }

    @GetMapping("/battery/{serialNumber}")
    public ResponseEntity<DroneBatteryDetailsRest> checkDroneBattery(
            @PathVariable String serialNumber) {

        DroneBatteryDetailsRest droneBatteryDetails = droneService.getBatteryLevel(serialNumber);
        return new ResponseEntity<DroneBatteryDetailsRest>(droneBatteryDetails, HttpStatus.OK);

    }

    @PostMapping("/load")
    public ResponseEntity<LoadDroneRest> loadDroneWithMedication(
            @Valid @NotNull @RequestBody LoadDroneRequest loadRequest) {
        LoadDroneRest loadDroneRest = droneService.loadDrone(loadRequest);
        return new ResponseEntity<LoadDroneRest>(loadDroneRest, HttpStatus.OK);
    }

    @GetMapping("/details/{serialNumber}")
    public ResponseEntity<DroneMedicationLoadRest> checkLoadedMedication(@PathVariable String serialNumber) {
        DroneMedicationLoadRest droneMedicationLoadRest = droneService.getMedicationLoad(serialNumber);
        return new ResponseEntity<DroneMedicationLoadRest>(droneMedicationLoadRest, HttpStatus.OK);
    }

    @GetMapping("/deliver/{serialNumber}")
    public ResponseEntity<DeliverDroneRest> deliverMedication(
        @PathVariable String serialNumber) {
        DeliverDroneRest deliverDroneRest = droneService.deliverLoad(serialNumber);
        return new ResponseEntity<DeliverDroneRest>(deliverDroneRest, HttpStatus.OK);
    }

    @GetMapping("/preload-data")
    public ResponseEntity<String> preloadData() {
        droneService.preLoadData();
        return new ResponseEntity<String>("Preloaded data", HttpStatus.OK);
    }
}

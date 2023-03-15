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

    @PostMapping("/battery")
    public ResponseEntity<DroneBatteryDetailsRest> checkDroneBattery(
            @NotNull @RequestBody(required = true) DroneGetBatteryRequest droneRequest) {

        DroneBatteryDetailsRest droneBatteryDetails = droneService.getBatteryLevel(droneRequest);
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

    @PostMapping("/deliver")
    public ResponseEntity<DeliverDroneRest> deliverMedication(
            @NotNull @RequestBody DroneDeliveryRequest deliveryRequest) {
        DeliverDroneRest deliverDroneRest = droneService.deliverLoad(deliveryRequest);
        return new ResponseEntity<DeliverDroneRest>(deliverDroneRest, HttpStatus.OK);
    }
}

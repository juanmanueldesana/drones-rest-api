package com.dronesserviceapi.interfaces;

import com.dronesserviceapi.models.requests.DroneRegisterRequest;
import com.dronesserviceapi.models.requests.LoadDroneRequest;
import com.dronesserviceapi.models.responses.AvailableDroneRest;
import com.dronesserviceapi.models.responses.DeliverDroneRest;
import com.dronesserviceapi.models.responses.DroneBatteryDetailsRest;
import com.dronesserviceapi.models.responses.DroneMedicationLoadRest;
import com.dronesserviceapi.models.responses.LoadDroneRest;
import com.dronesserviceapi.models.responses.RegisterDroneRest;

public interface DroneInterface {

    RegisterDroneRest registerDrone(DroneRegisterRequest drone);

    DroneBatteryDetailsRest getBatteryLevel(String serialNumber);

    DroneMedicationLoadRest getMedicationLoad(String serialNumber);

    AvailableDroneRest getAvailableDrones();

    LoadDroneRest loadDrone(LoadDroneRequest loadDroneRequest);

    DeliverDroneRest deliverLoad(String serialNumber);

    void preLoadData();

}

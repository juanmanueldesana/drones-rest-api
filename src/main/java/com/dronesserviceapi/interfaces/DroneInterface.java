package com.dronesserviceapi.interfaces;

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

public interface DroneInterface {

    RegisterDroneRest registerDrone(DroneRegisterRequest drone);

    DroneBatteryDetailsRest getBatteryLevel(DroneGetBatteryRequest drone);

    DroneMedicationLoadRest getMedicationLoad(String serialNumber);

    AvailableDroneRest getAvailableDrones();

    LoadDroneRest loadDrone(LoadDroneRequest loadDroneRequest);

    DeliverDroneRest deliverLoad(DroneDeliveryRequest droneDeliveryRequest);

    void preLoadData();

}

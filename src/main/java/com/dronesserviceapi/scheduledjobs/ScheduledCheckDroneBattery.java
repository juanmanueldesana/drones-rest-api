package com.dronesserviceapi.scheduledjobs;

import java.text.DecimalFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.dronesserviceapi.entities.DroneEntity;
import com.dronesserviceapi.repositories.DroneRepository;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class ScheduledCheckDroneBattery {

    @Autowired
    private DroneRepository droneRepository;

    static final Logger log = LoggerFactory.getLogger(ScheduledCheckDroneBattery.class);

    @Scheduled(fixedRate = 5000)
    public void scheduleFixedRateTaskAsync() throws InterruptedException {

        List<DroneEntity> drones = droneRepository.findAll();
        DecimalFormat decFormat = new DecimalFormat("#%");

        for (DroneEntity drone : drones) {

            log.info("Battery level--: SerialNumber " + drone.getSerialNumber() + " Battery level: "
                    + decFormat.format(drone.getBattery()) + " State: " + drone.getState().toString());

        }

        Thread.sleep(5000);

    }

}

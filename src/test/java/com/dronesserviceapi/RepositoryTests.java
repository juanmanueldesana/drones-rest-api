package com.dronesserviceapi;

import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.dronesserviceapi.entities.DroneEntity;
import com.dronesserviceapi.enums.Model;
import com.dronesserviceapi.enums.State;
import com.dronesserviceapi.repositories.DroneRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepositoryTests {

    @Autowired
    DroneRepository droneRepository;

    @Test
    public void testAddNewDrone() {
        DroneEntity drone = new DroneEntity("Q23RT5333697", Model.CRUISERWEIGHT, 800.00, new BigDecimal(0.95),
                State.IDLE);

        droneRepository.save(drone);

        Iterable<DroneEntity> drones = droneRepository.findAll();
        Assertions.assertThat(drones).extracting(DroneEntity::getSerialNumber).contains(drone.getSerialNumber());
        droneRepository.delete(drone);
        Assertions.assertThat(droneRepository.findById(drone.getSerialNumber()).isPresent()).isFalse();

    }

}

package com.dronesserviceapi;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dronesserviceapi.controllers.DroneController;

@SpringBootTest
class DronesApplicationTests {

	@Autowired
	DroneController droneController;

	@Test
	void contextLoads() {
		Assertions.assertThat(droneController).isNotNull();
	}

}

package com.dronesserviceapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.dronesserviceapi.entities.DroneEntity;
import com.dronesserviceapi.enums.Model;
import com.dronesserviceapi.enums.State;
import com.dronesserviceapi.models.responses.AvailableDroneRest;
import com.dronesserviceapi.repositories.DroneRepository;
import com.dronesserviceapi.services.DroneService;

@ExtendWith(MockitoExtension.class)
public class ServiceTests {

    @InjectMocks
    DroneService droneService;

    @Mock
    DroneRepository droneRepository;

    @Test
    public void testGetAvailableDrones() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        List<DroneEntity> list = new ArrayList<>();
        DroneEntity drone = new DroneEntity("Q23RT5333697", Model.CRUISERWEIGHT, 800.00, new BigDecimal(0.95),
                State.IDLE);
        DroneEntity drone2 = new DroneEntity("Q23RT5333698", Model.HEAVYWEIGHT, 500.00, new BigDecimal(0.95),
                State.IDLE);
        DroneEntity drone3 = new DroneEntity("Q23RT5333699", Model.LIGHTWEIGHT, 100.00, new BigDecimal(0.95),
                State.IDLE);
        DroneEntity drone4 = new DroneEntity("Q23RT5333690", Model.MIDDLEWEIGHT, 300.00, new BigDecimal(0.95),
                State.IDLE);

        list.add(drone);
        list.add(drone2);
        list.add(drone3);
        list.add(drone4);

        LocalDateTime time = LocalDateTime.now();
        AvailableDroneRest availableDroneRest = new AvailableDroneRest();
        availableDroneRest.setDrones(list);
        availableDroneRest.setTimestamp(time);

        when(droneRepository.findAllByState(State.IDLE)).thenReturn(list);

        AvailableDroneRest availableDrones = droneService.getAvailableDrones();
        assertEquals(4, availableDrones.getDrones().size());
        verify(droneRepository, times(1)).findAllByState(State.IDLE);

    }

}

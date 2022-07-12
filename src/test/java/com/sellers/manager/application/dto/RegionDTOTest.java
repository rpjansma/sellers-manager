package com.sellers.manager.application.dto;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RegionDTOTest {

    RegionDTO regionDTO;
    String name = "name";
    List<String> states = new ArrayList<>();
    Date dhRecordInclusion = new Date();
    Date dhUpdateRecord = new Date();

    @BeforeEach
    void setUp() {
        states.add("state1");
        states.add("state2");

        regionDTO = RegionDTO.builder()
                .name(name)
                .states(states)
                .dhRecordInclusion(dhRecordInclusion)
                .dhUpdateRecord(dhUpdateRecord)
                .build();
    }

    @Test
    @DisplayName("Test regionDTO getters")
    @Tag("unit")
    void getters_from_regionDTO_getting_successfully() {
        Assertions.assertEquals(name, regionDTO.getName());
        Assertions.assertEquals(states, regionDTO.getStates());
        Assertions.assertEquals(dhRecordInclusion, regionDTO.getDhRecordInclusion());
        Assertions.assertEquals(dhUpdateRecord, regionDTO.getDhUpdateRecord());
    }

    @Test
    @DisplayName("Test regionDTO setters")
    @Tag("unit")
    void setters_from_regionDTO_setting_successfully() {
        name = "newName";
        states = new ArrayList<>();
        dhRecordInclusion = new Date();
        dhUpdateRecord = new Date();

        regionDTO.setName(name);
        regionDTO.setStates(states);
        regionDTO.setDhRecordInclusion(dhRecordInclusion);
        regionDTO.setDhUpdateRecord(dhUpdateRecord);

        Assertions.assertEquals(name, regionDTO.getName());
        Assertions.assertEquals(states, regionDTO.getStates());
        Assertions.assertEquals(dhRecordInclusion, regionDTO.getDhRecordInclusion());
        Assertions.assertEquals(dhUpdateRecord, regionDTO.getDhUpdateRecord());
    }


}
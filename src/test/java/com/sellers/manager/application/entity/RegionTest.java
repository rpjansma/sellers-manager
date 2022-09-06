package com.sellers.manager.application.entity;

import com.sellers.manager.application.dto.RegionDTO;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Date;

class RegionTest {

    Region region;
    Integer id = 1;
    String name = "name";
    ArrayList<String> states = new ArrayList<>();
    Date dhRecordInclusion = new Date();
    Date dhUpdateRecord = new Date();

    @BeforeEach
    void setUp() {
        states.add("state1");
        states.add("state2");

        region = Region.builder()
                .id(id)
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
        Assertions.assertEquals(name, region.getName());
        Assertions.assertEquals(states, region.getStates());
        Assertions.assertEquals(dhRecordInclusion, region.getDhRecordInclusion());
        Assertions.assertEquals(dhUpdateRecord, region.getDhUpdateRecord());
    }

    @Test
    @DisplayName("Test regionDTO setters")
    @Tag("unit")
    void setters_from_regionDTO_setting_successfully() {
        id = 2;
        name = "newName";
        states = new ArrayList<>();

        region.setName(name);
        region.setStates(states);

        Assertions.assertEquals(name, region.getName());
        Assertions.assertEquals(states, region.getStates());
        Assertions.assertEquals(dhRecordInclusion, region.getDhRecordInclusion());
        Assertions.assertEquals(dhUpdateRecord, region.getDhUpdateRecord());
    }
}
package com.sellers.manager.application.dto;

import org.junit.jupiter.api.*;

import java.util.Date;

class SellerDTOTest {

    SellerDTO sellerDTO;
    Integer id = 1;
    String name = "name";
    String city = "city";
    String state = "SP";
    Integer age = 99;
    String phoneNumber = "123456789";
    String region = "sudeste";

    @BeforeEach
    void setUp() {
        sellerDTO = SellerDTO.builder()
                .id(id)
                .name(name)
                .age(age)
                .city(city)
                .phoneNumber(phoneNumber)
                .state(state)
                .region(region)
                .build();
    }

    @Test
    @DisplayName("Test sellerDTO getters")
    @Tag("unit")
    void getters_from_sellerDTO_getting_successfully() {
        Assertions.assertEquals(name, sellerDTO.getName());
        Assertions.assertEquals(age, sellerDTO.getAge());
        Assertions.assertEquals(city, sellerDTO.getCity());
        Assertions.assertEquals(phoneNumber, sellerDTO.getPhoneNumber());
        Assertions.assertEquals(region, sellerDTO.getRegion());
        Assertions.assertEquals(state, sellerDTO.getState());
    }

    @Test
    @DisplayName("Test sellerDTO setters")
    @Tag("unit")
    void setters_from_sellerDTO_setting_successfully() {

        id = 2;
        name = "newName";
        city = "newCity";
        state = "RJ";
        age = 19;
        phoneNumber = "987654321";
        region = "centro-oeste";

        sellerDTO.setId(id);
        sellerDTO.setName(name);
        sellerDTO.setAge(age);
        sellerDTO.setCity(city);
        sellerDTO.setState(state);
        sellerDTO.setRegion(region);
        sellerDTO.setPhoneNumber(phoneNumber);

        Assertions.assertEquals(name, sellerDTO.getName());
        Assertions.assertEquals(age, sellerDTO.getAge());
        Assertions.assertEquals(city, sellerDTO.getCity());
        Assertions.assertEquals(phoneNumber, sellerDTO.getPhoneNumber());
        Assertions.assertEquals(region, sellerDTO.getRegion());
        Assertions.assertEquals(state, sellerDTO.getState());
    }

}
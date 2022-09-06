package com.sellers.manager.application.entity;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

class SellerTest {

    Seller seller;
    Integer id = 1;
    String name = "name";
    String city = "city";
    String state = "SP";
    Integer age = 99;
    String phoneNumber = "123456789";
    @Mock
    Region region;
    Date dhRecordInclusion = new Date();
    Date dhUpdateRecord = new Date();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        seller = Seller.builder()
                .id(id)
                .name(name)
                .age(age)
                .city(city)
                .phoneNumber(phoneNumber)
                .state(state)
                .region(region)
                .dhRecordInclusion(dhRecordInclusion)
                .dhUpdateRecord(dhUpdateRecord)
                .build();
    }

    @Test
    @DisplayName("Test sellerDTO getters")
    @Tag("unit")
    void getters_from_sellerDTO_getting_successfully() {
        Assertions.assertEquals(name, seller.getName());
        Assertions.assertEquals(age, seller.getAge());
        Assertions.assertEquals(city, seller.getCity());
        Assertions.assertEquals(phoneNumber, seller.getPhoneNumber());
        Assertions.assertEquals(region, seller.getRegion());
        Assertions.assertEquals(state, seller.getState());
        Assertions.assertEquals(dhRecordInclusion, seller.getDhRecordInclusion());
        Assertions.assertEquals(dhUpdateRecord, seller.getDhUpdateRecord());
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
        region = Region.builder().build();
        dhRecordInclusion = new Date();
        dhUpdateRecord = new Date();

        seller.setName(name);
        seller.setAge(age);
        seller.setCity(city);
        seller.setState(state);
        seller.setRegion(region);
        seller.setPhoneNumber(phoneNumber);

        Assertions.assertEquals(name, seller.getName());
        Assertions.assertEquals(age, seller.getAge());
        Assertions.assertEquals(city, seller.getCity());
        Assertions.assertEquals(phoneNumber, seller.getPhoneNumber());
        Assertions.assertEquals(region, seller.getRegion());
        Assertions.assertEquals(state, seller.getState());
    }
}
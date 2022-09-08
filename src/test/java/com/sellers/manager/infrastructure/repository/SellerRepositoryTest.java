package com.sellers.manager.infrastructure.repository;

import com.sellers.manager.application.entity.Seller;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;

@DataJpaTest
@ActiveProfiles("test")
class SellerRepositoryTest {

    @Autowired
    SellerRepository sellerRepository;
    Seller seller;

    @BeforeEach
    void setUp() {
        seller = Seller.builder()
                .id(1)
                .name("name")
                .dhRecordInclusion(new Date())
                .dhUpdateRecord(new Date())
                .build();
    }

    @Test
    @DisplayName("Tests bd connection")
    @Tag("unit")
    void connect_and_get_data_from_datasql() {
        Assertions.assertEquals(0, sellerRepository.findAll().size());
    }

    @Test
    @DisplayName("Tests saving a new entity")
    @Tag("unit")
    void save_new_entity() {
        sellerRepository.save(seller);
        Assertions.assertEquals(seller.getId(), sellerRepository.getById(1).getId());
    }
}
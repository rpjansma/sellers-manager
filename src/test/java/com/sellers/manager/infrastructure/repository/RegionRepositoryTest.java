package com.sellers.manager.infrastructure.repository;

import com.sellers.manager.application.entity.Region;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Date;

@DataJpaTest
@ActiveProfiles("test")
class RegionRepositoryTest {

    @Autowired
    RegionRepository regionRepository;
    Region region;

    @BeforeEach
    void setUp() {
        region = Region.builder()
                .id(1)
                .name("name")
                .states(new ArrayList<>())
                .dhRecordInclusion(new Date())
                .dhUpdateRecord(new Date())
                .build();
    }

    @Test
    @DisplayName("Tests bd connection")
    @Tag("unit")
    void connect_and_get_data_from_datasql() {
        Assertions.assertEquals(5, regionRepository.findAll().size());
    }
}
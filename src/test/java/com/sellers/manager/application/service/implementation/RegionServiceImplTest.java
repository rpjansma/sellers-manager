package com.sellers.manager.application.service.implementation;

import com.sellers.manager.application.assembler.RegionAssembler;
import com.sellers.manager.application.dto.RegionDTO;
import com.sellers.manager.application.entity.Region;
import com.sellers.manager.application.gateway.RegionGateway;
import com.sellers.manager.application.service.implementation.RegionServiceImpl;
import com.sellers.manager.application.validator.RegionValidator;
import com.sellers.manager.userinterface.exception.BadRequestException;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;

class RegionServiceImplTest {

    @Mock
    RegionGateway regionGateway;
    @Mock
    RegionValidator regionValidator;
    @Mock
    RegionAssembler regionAssembler;

    RegionServiceImpl regionServiceImpl;
    Region region;
    RegionDTO regionDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        regionServiceImpl = new RegionServiceImpl(regionGateway, regionValidator, regionAssembler);
        region = Region.builder().name("sudeste").build();
        regionDTO = RegionDTO.builder().name("sudeste").build();
    }

    @Test
    @DisplayName("Testing GET for all regions list")
    @Tag("unit")
    void getAllRegions() {
        List<Region> regionList = new ArrayList<>();
        List<RegionDTO> regionDTOList = new ArrayList<>();
        regionList.add(region);
        regionDTOList.add(regionDTO);

        Mockito.when(regionGateway.getAllRegions()).thenReturn(regionList);
        Mockito.when(regionAssembler.toRegionDTO(region)).thenReturn(regionDTO);

        Assertions.assertEquals(regionDTOList, regionServiceImpl.getAllRegions());
        Mockito.verify(regionGateway, times(1)).getAllRegions();
        Mockito.verify(regionAssembler, atLeast(1)).toRegionDTO(region);
    }

    @Test
    @DisplayName("Testing getting region by name")
    @Tag("unit")
    void getByName() {
        Mockito.when(regionGateway.getByName(regionDTO.getName())).thenReturn(Optional.ofNullable(region));

        Assertions.assertEquals(region, regionServiceImpl.getByName(regionDTO.getName()));
        Mockito.verify(regionGateway, times(1)).getByName(regionDTO.getName());
    }

    @Test
    @DisplayName("Testing exception on getByName")
    @Tag("unit")
    void getByNameUnsuccessfully() {
        Optional<Region> emptyRegion = Optional.empty();

        Mockito.when(regionGateway.getByName(regionDTO.getName())).thenReturn(emptyRegion);

        Assertions.assertThrows(BadRequestException.class, () -> regionServiceImpl.getByName(regionDTO.getName()));
    }

    @Test
    @DisplayName("Testing creation of a region")
    @Tag("unit")
    void createRegion() {
        List<Region> regionList = new ArrayList<>();
        regionList.add(region);

        Mockito.when(regionGateway.getAllRegions()).thenReturn(regionList);
        Mockito.doNothing().when(regionValidator).regionUniqueName(regionList, regionDTO);
        Mockito.when(regionAssembler.toRegion(regionDTO)).thenReturn(region);
        Mockito.when(regionGateway.save(region)).thenReturn(region);
        Mockito.when(regionAssembler.toRegionDTO(region)).thenReturn(regionDTO);

        Assertions.assertEquals(regionDTO, regionServiceImpl.createRegion(regionDTO));
        Mockito.verify(regionValidator, times(1)).regionUniqueName(regionList, regionDTO);
        Mockito.verify(regionAssembler, times(1)).toRegion(regionDTO);
        Mockito.verify(regionGateway, times(1)).save(region);
        Mockito.verify(regionAssembler, times(1)).toRegionDTO(region);
    }
}
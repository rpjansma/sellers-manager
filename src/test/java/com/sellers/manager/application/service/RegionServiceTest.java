package com.sellers.manager.application.service;

import com.sellers.manager.application.assembler.RegionAssembler;
import com.sellers.manager.application.dto.RegionDTO;
import com.sellers.manager.application.entity.Region;
import com.sellers.manager.application.gateway.RegionGateway;
import com.sellers.manager.application.validator.RegionValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;

class RegionServiceTest {

    @Mock
    RegionGateway regionGateway;
    @Mock
    RegionValidator regionValidator;
    @Mock
    RegionAssembler regionAssembler;

    RegionService regionService;
    Region region;
    RegionDTO regionDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        regionService = new RegionService(regionGateway, regionValidator, regionAssembler);
        region = Region.builder().name("sudeste").build();
        regionDTO = RegionDTO.builder().name("sudeste").build();

    }

    @Test
    void getAllRegions() {
        List<Region> regionList = new ArrayList<>();
        List<RegionDTO> regionDTOList = new ArrayList<>();
        regionList.add(region);
        regionDTOList.add(regionDTO);

        Mockito.when(regionGateway.getAllRegions()).thenReturn(regionList);
        Mockito.when(regionAssembler.toRegionDTO(region)).thenReturn(regionDTO);

        Assertions.assertEquals(regionDTOList, regionService.getAllRegions());
        Mockito.verify(regionGateway, times(1)).getAllRegions();
        Mockito.verify(regionAssembler, atLeast(1)).toRegionDTO(region);
    }

    @Test
    void getByName() {
        Mockito.when(regionGateway.getByName(regionDTO.getName())).thenReturn(Optional.ofNullable(region));

        Assertions.assertEquals(region, regionService.getByName(regionDTO.getName()));
        Mockito.verify(regionGateway, times(1)).getByName(regionDTO.getName());
    }

    @Test
    void createRegion() {
        List<Region> regionList = new ArrayList<>();
        regionList.add(region);

        Mockito.when(regionGateway.getAllRegions()).thenReturn(regionList);
        Mockito.doNothing().when(regionValidator).regionUniqueName(regionList, regionDTO);
        Mockito.when(regionAssembler.toRegion(regionDTO)).thenReturn(region);
        Mockito.when(regionGateway.save(region)).thenReturn(region);
        Mockito.when(regionAssembler.toRegionDTO(region)).thenReturn(regionDTO);

        Assertions.assertEquals(regionDTO, regionService.createRegion(regionDTO));
        Mockito.verify(regionValidator, times(1)).regionUniqueName(regionList, regionDTO);
        Mockito.verify(regionAssembler, times(1)).toRegion(regionDTO);
        Mockito.verify(regionGateway, times(1)).save(region);
        Mockito.verify(regionAssembler, times(1)).toRegionDTO(region);
    }
}
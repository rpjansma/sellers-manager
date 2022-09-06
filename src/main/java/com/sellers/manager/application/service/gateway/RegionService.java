package com.sellers.manager.application.service.gateway;

import com.sellers.manager.application.dto.RegionDTO;
import com.sellers.manager.application.entity.Region;

import java.util.List;

public interface RegionService {
    List<RegionDTO> getAllRegions();
    Region getByName(String name);
    RegionDTO createRegion(RegionDTO regionDTO);
}

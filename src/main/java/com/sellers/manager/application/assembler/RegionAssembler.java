package com.sellers.manager.application.assembler;

import com.sellers.manager.application.dto.RegionDTO;
import com.sellers.manager.application.entity.Region;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RegionAssembler {

    ModelMapper mapper;

    public RegionAssembler() {
        this.mapper = new ModelMapper();
    }

    public Region toRegion(RegionDTO regionDTO) {
        return mapper.map(regionDTO, Region.class);
    }

    public RegionDTO toRegionDTO(Region region) {
        return mapper.map(region, RegionDTO.class);
    }
}

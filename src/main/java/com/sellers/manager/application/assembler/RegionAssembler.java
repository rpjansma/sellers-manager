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

    public Region toRegion(RegionDTO brandDTO) {
        return mapper.map(brandDTO, Region.class);
    }

    public RegionDTO toRegionDTO(Region brand) {
        return mapper.map(brand, RegionDTO.class);
    }
}

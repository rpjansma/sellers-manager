package com.sellers.manager.application.validator;

import com.sellers.manager.application.dto.RegionDTO;
import com.sellers.manager.application.entity.Region;
import com.sellers.manager.userinterface.exception.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class RegionValidator {

    public void regionUniqueName(List<Region> regionList, RegionDTO regionDTO) {
        if (regionList.stream().anyMatch(region -> region.getName().equals(regionDTO.getName())))
            throw new BadRequestException("A região já existe: " + regionDTO.getName());
    }

}

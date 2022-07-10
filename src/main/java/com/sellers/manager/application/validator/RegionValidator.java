package com.sellers.manager.application.validator;

import com.sellers.manager.application.dto.RegionDTO;
import com.sellers.manager.application.entity.Region;
import com.sellers.manager.userinterface.exception.BadRequestException;
import com.sellers.manager.userinterface.exception.InternalServerErrorException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class RegionValidator {


    public void regionListValidationOnDatabase(List<Region> brandsOnDatabase) {
        if (brandsOnDatabase.isEmpty())
            throw new InternalServerErrorException("Erro interno no servidor.");
    }

    public void regionUniqueName(List<Region> brandsRegistered, RegionDTO regionDTO) {
        if (brandsRegistered.stream().anyMatch(region -> region.getRegionName().equals(regionDTO.getRegionName())))
            throw new BadRequestException("A região já existe: " + regionDTO.getRegionName());
    }

    public void checkRegionPresent(Optional<Region> brand) {
        if (brand.isEmpty())
            throw new BadRequestException("Região não localizada.");
    }

}

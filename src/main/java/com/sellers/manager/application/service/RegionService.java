package com.sellers.manager.application.service;

import com.sellers.manager.application.assembler.RegionAssembler;
import com.sellers.manager.application.dto.RegionDTO;
import com.sellers.manager.application.entity.Region;
import com.sellers.manager.application.gateway.RegionGateway;
import com.sellers.manager.application.validator.RegionValidator;
import com.sellers.manager.userinterface.exception.BadRequestException;
import com.sellers.manager.userinterface.exception.NoContentException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RegionService {

    private final RegionGateway regionGateway;
    private final RegionValidator regionValidator;
    private final RegionAssembler regionAssembler;

    public List<RegionDTO> getAllRegions() {
        List<RegionDTO> regionList = regionGateway.getAllRegions().stream().map(regionAssembler::toRegionDTO).collect(Collectors.toList());
        if(regionList.isEmpty()) throw new NoContentException("Regiões não localizados.");

        return regionList;
    }

    public Region getByName(String name) {
        Optional<Region> region = Optional.ofNullable(regionGateway.getByName(name)
                .orElseThrow(() -> new BadRequestException("A Região não foi localizada.")));

        return region.get();
    }

    public RegionDTO createRegion(RegionDTO regionDTO) {

        List<Region> regionsRegistered = regionGateway.getAllRegions();
        regionValidator.regionUniqueName(regionsRegistered, regionDTO);

        Region region = regionAssembler.toRegion(regionDTO);
        regionGateway.save(region);

        return regionAssembler.toRegionDTO(region);
    }

}

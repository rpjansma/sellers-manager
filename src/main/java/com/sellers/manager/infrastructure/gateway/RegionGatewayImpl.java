package com.sellers.manager.infrastructure.gateway;

import com.sellers.manager.application.entity.Region;
import com.sellers.manager.application.gateway.RegionGateway;
import com.sellers.manager.infrastructure.repository.RegionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class RegionGatewayImpl implements RegionGateway {

    private final RegionRepository regionRepository;

    @Override
    public Optional<Region> getById(Integer id) {
        return regionRepository.findById(id);
    }

    @Override
    public Region getByName(String regionName) {
        return regionRepository.getByName(regionName);
    }

    @Override
    public List<Region> getByNames(Collection<String> names) {
        return regionRepository.getByNames(names);
    }

    @Override
    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    @Override
    public Region save(Region region) {
        return regionRepository.save(region);
    }

    @Override
    public void deleteRegion(Integer RegionId) {
        regionRepository.deleteById(RegionId);
    }
}

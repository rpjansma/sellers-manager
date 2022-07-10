package com.sellers.manager.application.gateway;


import com.sellers.manager.application.entity.Region;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface RegionGateway {

    Optional<Region> getById(Integer id);

    Region getByName(String name);

    List<Region> getByNames(Collection<String> names);

    List<Region> getAllRegions();

    Region save(Region Region);

    void deleteRegion(Integer integer);

}

package com.sellers.manager.application.gateway;


import com.sellers.manager.application.entity.Region;

import java.util.List;
import java.util.Optional;

public interface RegionGateway {

    Optional<Region> getByName(String name);

    List<Region> getAllRegions();

    Region save(Region region);

}

package com.sellers.manager.application.gateway;


import com.sellers.manager.application.entity.Region;
import com.sellers.manager.application.entity.Seller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface SellerGateway {

    Optional<Seller> getById(Integer id);

    Seller getByName(String name);

    List<Seller> getByNames(Collection<String> names);

    List<Seller> getAllSeller();

    Seller save(Seller seller);

    void deleteRegion(Integer id);

}

package com.sellers.manager.application.gateway;


import com.sellers.manager.application.entity.Seller;

import java.util.List;
import java.util.Optional;

public interface SellerGateway {

    Optional<Seller> getById(Integer id);

    List<Seller> getAllSellers();

    Seller save(Seller seller);
}

package com.sellers.manager.infrastructure.gateway;

import com.sellers.manager.application.entity.Region;
import com.sellers.manager.application.entity.Seller;
import com.sellers.manager.application.gateway.RegionGateway;
import com.sellers.manager.application.gateway.SellerGateway;
import com.sellers.manager.infrastructure.repository.SellerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class SellerGatewayImpl implements SellerGateway {

    private final SellerRepository sellerRepository;


    @Override
    public Optional<Seller> getById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Seller getByName(String name) {
        return null;
    }

    @Override
    public List<Seller> getByNames(Collection<String> names) {
        return null;
    }

    @Override
    public List<Seller> getAllSeller() {
        return null;
    }

    @Override
    public Seller save(Seller seller) {
        return null;
    }

    @Override
    public void deleteRegion(Integer integer) {
    }
}

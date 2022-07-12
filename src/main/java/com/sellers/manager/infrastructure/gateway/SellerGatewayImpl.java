package com.sellers.manager.infrastructure.gateway;

import com.sellers.manager.application.entity.Seller;
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
        return sellerRepository.findById(id);
    }

    @Override
    public Seller getByName(String name) {
        return sellerRepository.getByName(name);
    }

    @Override
    public List<Seller> getByNames(Collection<String> names) {
        return sellerRepository.getByNames(names);
    }

    @Override
    public List<Seller> getAllSeller() {
        return sellerRepository.findAll();
    }

    @Override
    public Seller save(Seller seller) {
        return sellerRepository.save(seller);
    }

    @Override
    public void deleteRegion(Integer id) {
        sellerRepository.deleteById(id);
    }
}

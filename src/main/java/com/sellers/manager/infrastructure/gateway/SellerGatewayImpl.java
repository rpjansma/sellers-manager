package com.sellers.manager.infrastructure.gateway;

import com.sellers.manager.application.entity.Seller;
import com.sellers.manager.application.gateway.SellerGateway;
import com.sellers.manager.infrastructure.repository.SellerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

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
    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }

    @Override
    public Seller save(Seller seller) {
        return sellerRepository.save(seller);
    }

}

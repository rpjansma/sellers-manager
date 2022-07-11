package com.sellers.manager.application.service;

import com.sellers.manager.application.assembler.SellerAssembler;
import com.sellers.manager.application.dto.SellerDTO;
import com.sellers.manager.application.entity.Seller;
import com.sellers.manager.application.gateway.SellerGateway;
import com.sellers.manager.application.validator.SellerValidator;
import com.sellers.manager.userinterface.exception.NoContentException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SellerService {

    private final SellerGateway sellerGateway;
    private final SellerValidator sellerValidator;
    private final SellerAssembler sellerAssembler;

    public List<Seller> getAllSellers() {
        return sellerGateway.getAllSeller();
    }

    public SellerDTO getById(Integer sellerId) {
        Optional<Seller> seller = Optional.ofNullable(sellerGateway.getById(sellerId)
                .orElseThrow(() -> new NoContentException("Vendedor não localizado.")));

        return sellerAssembler.toSellerDTO(seller.get());
    }

    public SellerDTO createSeller(SellerDTO sellerDTO) {

        List<Seller> sellers = sellerGateway.getAllSeller();
        sellerValidator.sellerUniqueName(sellers, sellerDTO);

        Seller seller = sellerAssembler.toSeller(sellerDTO);
        sellerGateway.save(seller);

        return sellerAssembler.toSellerDTO(seller);
    }

    public void pathSeller(SellerDTO sellerDTO, Integer regionId) {
        Optional.ofNullable(sellerGateway.getById(regionId)
                .orElseThrow(() -> new NoContentException("Vendedor não localizado.")));
        Seller seller = sellerAssembler.toSeller(sellerDTO);

        sellerGateway.save(seller);
    }

    public void deleteSeller(Integer regionId) {
        Optional.ofNullable(sellerGateway.getById(regionId)
                .orElseThrow(() -> new NoContentException("Vendedor não localizado")));
        sellerGateway.deleteRegion(regionId);
    }

}
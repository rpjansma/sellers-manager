package com.sellers.manager.application.service;

import com.sellers.manager.application.assembler.SellerAssembler;
import com.sellers.manager.application.dto.SellerDTO;
import com.sellers.manager.application.dto.SellerDataAndStatesDTO;
import com.sellers.manager.application.dto.SellerWithStatesDTO;
import com.sellers.manager.application.entity.Region;
import com.sellers.manager.application.entity.Seller;
import com.sellers.manager.application.gateway.SellerGateway;
import com.sellers.manager.application.validator.SellerValidator;
import com.sellers.manager.userinterface.exception.NoContentException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SellerService {

    private final SellerGateway sellerGateway;
    private final SellerValidator sellerValidator;
    private final SellerAssembler sellerAssembler;
    private final RegionService regionService;

    public List<SellerDataAndStatesDTO> getAllSellers() {
        List<SellerDataAndStatesDTO> sellersList = sellerGateway.getAllSellers().stream().map(sellerAssembler::toSellerDataAndStatesDTO).collect(Collectors.toList());
        if(sellersList.isEmpty()) throw new NoContentException("Vendedores não localizados.");

        return sellersList;
    }

    public SellerWithStatesDTO getById(Integer sellerId) {
        Optional<Seller> seller = Optional.ofNullable(sellerGateway.getById(sellerId)
                .orElseThrow(() -> new NoContentException("Vendedor não localizado.")));

        return sellerAssembler.toSellerWithStatesDTO(seller.get());
    }

    public SellerWithStatesDTO createSeller(SellerDTO sellerDTO) {
        List<Seller> sellers = sellerGateway.getAllSellers();
        sellerValidator.sellerUniqueName(sellers, sellerDTO);

        Region region = regionService.getByName(sellerDTO.getRegion());

        Seller seller = sellerAssembler.toSeller(sellerDTO);
        seller.setRegion(region);
        sellerGateway.save(seller);

        SellerWithStatesDTO sellerWithStatesDTO = sellerAssembler.toSellerWithStatesDTO(seller);
        sellerWithStatesDTO.setStates(region.getStates());

        return sellerWithStatesDTO;
    }

}

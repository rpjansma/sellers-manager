package com.sellers.manager.application.assembler;

import com.sellers.manager.application.dto.SellerDTO;
import com.sellers.manager.application.entity.Seller;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SellerAssembler {

    ModelMapper mapper;

    public SellerAssembler() {
        this.mapper = new ModelMapper();
    }

    public Seller toSeller(SellerDTO sellerDTO) {
        return mapper.map(sellerDTO, Seller.class);
    }

    public SellerDTO toSellerDTO(Seller seller) {
        return mapper.map(seller, SellerDTO.class);
    }
}

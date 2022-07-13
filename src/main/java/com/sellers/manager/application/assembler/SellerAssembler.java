package com.sellers.manager.application.assembler;

import com.sellers.manager.application.dto.SellerDTO;
import com.sellers.manager.application.dto.SellerDataAndStatesDTO;
import com.sellers.manager.application.dto.SellerWithStatesDTO;
import com.sellers.manager.application.entity.Seller;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
public class SellerAssembler {

    ModelMapper mapper;

    public SellerAssembler() {
        this.mapper = new ModelMapper();
        TypeMap<Seller, SellerDataAndStatesDTO> sellerMapper = this.mapper.createTypeMap(Seller.class, SellerDataAndStatesDTO.class);
        sellerMapper.addMapping(Seller::getRegionStates , SellerDataAndStatesDTO::setStates);
    }

    public Seller toSeller(SellerDTO sellerDTO) {
        return mapper.map(sellerDTO, Seller.class);
    }

    public SellerDTO toSellerDTO(Seller seller) {
        return mapper.map(seller, SellerDTO.class);
    }

    public SellerWithStatesDTO toSellerWithStatesDTO(Seller seller) {
        return mapper.map(seller, SellerWithStatesDTO.class);
    }

    public SellerDataAndStatesDTO toSellerDataAndStatesDTO(Seller seller) {
        return mapper.map(seller, SellerDataAndStatesDTO.class);
    }
}

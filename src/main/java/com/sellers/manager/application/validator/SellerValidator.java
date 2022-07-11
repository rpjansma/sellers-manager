package com.sellers.manager.application.validator;

import com.sellers.manager.application.dto.SellerDTO;
import com.sellers.manager.application.entity.Seller;
import com.sellers.manager.userinterface.exception.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class SellerValidator {

    public void sellerUniqueName(List<Seller> sellerList, SellerDTO sellerDTO) {
        if (sellerList.stream().anyMatch(region -> region.getName().equals(sellerDTO.getName())))
            throw new BadRequestException("O usuário " + sellerDTO.getName() + " já existe.");
    }

    public void checkSellerPresent(Optional<Seller> seller) {
        if (seller.isEmpty())
            throw new BadRequestException("Usuário não localizado.");
    }

}

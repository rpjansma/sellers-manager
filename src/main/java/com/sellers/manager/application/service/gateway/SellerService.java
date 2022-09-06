package com.sellers.manager.application.service.gateway;

import com.sellers.manager.application.dto.SellerDTO;
import com.sellers.manager.application.dto.SellerDataAndStatesDTO;
import com.sellers.manager.application.dto.SellerWithStatesDTO;

import java.util.List;

public interface SellerService {
    List<SellerDataAndStatesDTO> getAllSellers();
    SellerWithStatesDTO getById(Integer sellerId);
    SellerWithStatesDTO createSeller(SellerDTO sellerDTO);
}

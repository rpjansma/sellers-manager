package com.sellers.manager.userinterface.controller;

import com.sellers.manager.application.dto.SellerDTO;
import com.sellers.manager.application.service.SellerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("vendedor")
public class SellersController {

    private final SellerService sellerService;

    @GetMapping(value = "/", name = "Consulta de todas os vendedores")
    public ResponseEntity<List<SellerDTO>> getAllActuationRegions() {
        return ResponseEntity.ok(sellerService.getAllSellers());
    }

    @GetMapping(value = "/{sellerId}", name = "Consulta de vendedores por Id")
    public ResponseEntity getSelllerById(@PathVariable Integer sellerId) {
        return ResponseEntity.ok(sellerService.getById(sellerId));
    }


    @PostMapping(value = "/", name = "Cria novo vendedor")
    public ResponseEntity post(@Valid @RequestBody SellerDTO sellerDTO) {
        return new ResponseEntity(sellerService.createSeller(sellerDTO), HttpStatus.CREATED);
    }


}

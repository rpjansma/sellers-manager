package com.sellers.manager.userinterface.controller;

import com.sellers.manager.application.dto.SellerDTO;
import com.sellers.manager.application.dto.SellerDataAndStatesDTO;
import com.sellers.manager.application.service.gateway.SellerService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("v1/vendedores")
@Api(tags = {"Vendedores"})
@Tag(name = "Vendedores", description = "Recurso para consultar e criar vendedores")
public class SellersController {

    private final SellerService sellerService;

    @GetMapping(name = "Consulta de todas os vendedores")
    public ResponseEntity<List<SellerDataAndStatesDTO>> getAllActuationRegions() {
        return ResponseEntity.ok(sellerService.getAllSellers());
    }

    @GetMapping(value = "/{sellerId}", name = "Consulta de vendedores por Id")
    public ResponseEntity getSelllerById(@PathVariable Integer sellerId) {
        return ResponseEntity.ok(sellerService.getById(sellerId));
    }

    @PostMapping(name = "Cria novo vendedor")
    public ResponseEntity post(@Valid @RequestBody SellerDTO sellerDTO) {
        return ResponseEntity.created(URI.create("/v1/vendedores")).body(sellerService.createSeller(sellerDTO));
    }


}

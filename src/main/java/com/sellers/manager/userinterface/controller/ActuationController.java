package com.sellers.manager.userinterface.controller;

import com.sellers.manager.application.dto.RegionDTO;
import com.sellers.manager.application.service.gateway.RegionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("v1/regioes")
public class ActuationController {

    private final RegionService regionService;

    @GetMapping(name = "Consulta de todas as regiões")
    public ResponseEntity<List<RegionDTO>> getAllActuationRegions() {
        return ResponseEntity.ok(regionService.getAllRegions());
    }


    @PostMapping(name = "Cria nova região")
    public ResponseEntity<RegionDTO> post(@Valid @RequestBody RegionDTO regionDTO) {
        return new ResponseEntity<RegionDTO>(regionService.createRegion(regionDTO), HttpStatus.CREATED);
    }


}

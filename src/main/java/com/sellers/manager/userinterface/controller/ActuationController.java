package com.sellers.manager.userinterface.controller;

import com.sellers.manager.application.dto.RegionDTO;
import com.sellers.manager.application.service.RegionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("atuacao")
public class ActuationController {

    private final RegionService regionService;

    @GetMapping(value = "/", name = "Consulta de todas as regiões")
    public ResponseEntity getAllActuationRegions() {
        return ResponseEntity.ok(regionService.getAllRegions());
    }


    @PostMapping(value = "/", name = "Cria nova região")
    public ResponseEntity post(@Valid @RequestBody RegionDTO regionDTO) {
        return ResponseEntity.ok(regionService.createRegion(regionDTO));
    }


}

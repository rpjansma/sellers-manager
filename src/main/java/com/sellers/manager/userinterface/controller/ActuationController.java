package com.sellers.manager.userinterface.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("atuacao")
public class ActuationController {


    @GetMapping(value = "/", name = "Consulta de todas as regiões")
    public ResponseEntity get() {
        return ResponseEntity.ok("");
    }


    @PostMapping(value = "/", name = "Cria nova região")
    public ResponseEntity post() {
        return ResponseEntity.ok("");
    }


}

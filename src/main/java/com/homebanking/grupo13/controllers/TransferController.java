package com.homebanking.grupo13.controllers;


import com.homebanking.grupo13.entities.dtos.TransferDto;
import com.homebanking.grupo13.services.TransferService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/transfers")
@AllArgsConstructor
public class TransferController {

    private final TransferService service;

    @GetMapping
    public ResponseEntity<List<TransferDto>> getTransfers() {
        List<TransferDto> transfers = service.getTransfers();
        return ResponseEntity.status(HttpStatus.OK).body(transfers);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TransferDto> getTransferById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getTransferById(id));
    }

    @PostMapping
    public ResponseEntity<TransferDto> createTransfer(@Valid @RequestBody TransferDto transfer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createTransfer(transfer));
    }


}

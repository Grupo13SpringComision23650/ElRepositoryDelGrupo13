package com.homebanking.grupo13.controllers;


import com.homebanking.grupo13.entities.dtos.TransferDTO;
import com.homebanking.grupo13.services.TransferService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/transfers")
public class TransferController {

    @Autowired
    private TransferService service;

    @GetMapping
    public ResponseEntity<List<TransferDTO>> getTransfers() {
        List<TransferDTO> transfers = service.getTransfers();
        return ResponseEntity.status(HttpStatus.OK).body(transfers);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TransferDTO> getTransferById(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getTransferById(id));
    }

    @PostMapping
    public ResponseEntity<TransferDTO> createTransfer(@Valid @RequestBody TransferDTO transfer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createTransfer(transfer));
    }


}

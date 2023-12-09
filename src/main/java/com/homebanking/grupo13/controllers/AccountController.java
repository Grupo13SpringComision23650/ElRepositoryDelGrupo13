package com.homebanking.grupo13.controllers;


import com.homebanking.grupo13.entities.dtos.AccountDto;
import com.homebanking.grupo13.services.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Scanner;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService service;
    private AccountController(AccountService service){
        this.service = service;
    }
    @GetMapping(value = "/accounts")
    public ResponseEntity<List<AccountDto>> getAccounts(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAccounts());
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAccountById(id));
    }
    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@Valid @RequestBody AccountDto account){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createAccount(account));
    }
    @PutMapping(value="/{id}")
    public ResponseEntity<AccountDto> updateAccount(@Valid @PathVariable Long id, @RequestBody AccountDto account){
        return ResponseEntity.status(HttpStatus.OK).body(service.updateAccount(id, account));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteAccount(id));
    }
}

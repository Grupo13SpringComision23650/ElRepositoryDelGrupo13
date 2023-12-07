package com.homebanking.grupo13.services;


import com.homebanking.grupo13.entities.Account;
import com.homebanking.grupo13.entities.Transfer;
import com.homebanking.grupo13.entities.dtos.TransferDTO;
import com.homebanking.grupo13.mappers.TransferMapper;
import com.homebanking.grupo13.repositories.AccountRepository;
import com.homebanking.grupo13.repositories.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferService {
    private AccountRepository accountRepository;

    @Autowired
    private TransferRepository repository;

    /* TODO: Implementar este metodo cuando Account (o User) este disponible
    public TransferDTO transfer(Account source,Account destine,BigDecimal amount){

    }
    */

    public List<TransferDTO> getTransfers() {
        return repository
                .findAll()
                .stream()
                .map(TransferMapper::transferToDto)
                .collect(Collectors.toList());
    }

    public TransferDTO getTransferById(Long id) {
        Transfer transfer = repository.findById(id).get();
        return TransferMapper.transferToDto(transfer);
    }

    public TransferDTO createTransfer(TransferDTO dto) {
        /*
        Transfer transfer = TransferMapper.dtoToTransfer(dto);
        transfer.setCreateAt(LocalDateTime.now());

        // TODO: validar transferencia, no puede haber transferencia
        //      si la cuenta origen no tiene fondo suficiente
        validateTransfer(dto);
        repository.save(transfer);

        TransferDTO dto1 = TransferMapper.transferToDto(transfer);
        return dto1;
        */

        // Comprobar si las cuentas de origen y destino existen
        /*
        Account originAccount = accountRepository.findById(dto.getOrigin())
                .orElseThrow(() -> new AccountNotFoundException("Account not found with id: " + dto.getOrigin()));
        Account destinationAccount = accountRepository.findById(dto.getTarget())
                .orElseThrow(() -> new AccountNotFoundException("Account not found with id: " + dto.getTarget()));
        */
        // Comprobar si la cuenta de origen tiene fondos suficientes
        Account accountSource=accountRepository.getReferenceById(dto.getAccountSourceId());
        Account accountDestine=accountRepository.getReferenceById(dto.getAccountDestineId());

        if (accountSource.getAmount().compareTo(dto.getAmount()) < 0) {
            //throw new InsufficientFoundsException("Insufficient funds in the account with id: " + dto.getOrigin());
            throw new RuntimeException("No hay fondos insuficientes");
        }

        // Realizar la transferencia
        accountSource.setAmount(accountSource.getAmount().subtract(dto.getAmount()));
        accountDestine.setAmount(accountDestine.getAmount().add(dto.getAmount()));

        // Guardar las cuentas actualizadas
        accountRepository.save(accountDestine);
        accountRepository.save(accountSource);

        // Crear la transferencia y guardarla en la base de datos
        Transfer transfer = new Transfer();
        // Creamos un objeto del tipo Date para obtener la fecha actual

        // Seteamos el objeto fecha actual en el transferDto

        transfer.setAccountSourceId(accountSource.getId());
        transfer.setAccountDestineId(accountDestine.getId());
        transfer.setAmount(dto.getAmount());
        transfer = repository.save(transfer);

        // Devolver el DTO de la transferencia realizada
        return TransferMapper.transferToDto(transfer);

    }



    public Transfer validateTransfer(TransferDTO dto){
        Transfer transfer=TransferMapper.dtoToTransfer(dto);
        // TODO: validar



        return transfer;
    }


}


package com.homebanking.grupo13.services;


import com.homebanking.grupo13.entities.Transfer;
import com.homebanking.grupo13.entities.dtos.TransferDTO;
import com.homebanking.grupo13.mappers.TransferMapper;
import com.homebanking.grupo13.repositories.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferService {

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
        Transfer transfer = TransferMapper.dtoToTransfer(dto);
        transfer.setCreateAt(Timestamp.from(Instant.now()));

        // TODO: validar transferencia, no puede haber transferencia
        //      si la cuenta origen no tiene fondo suficiente
        Transfer transfer1=repository.save(transfer);

        TransferDTO dto1 = TransferMapper.transferToDto(transfer1);
        return dto1;
    }


    /*  TODO: Implementar este metodo cuando Account este disponible
    public Transfer validateTransfer(TransferDTO dto){


    }

     */
}


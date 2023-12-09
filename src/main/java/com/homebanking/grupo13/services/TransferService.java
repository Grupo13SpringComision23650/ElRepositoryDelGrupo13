package com.homebanking.grupo13.services;


import com.homebanking.grupo13.entities.Account;
import com.homebanking.grupo13.entities.Transfer;
import com.homebanking.grupo13.entities.dtos.TransferDTO;
import com.homebanking.grupo13.exceptions.AccountNotFoundException;
import com.homebanking.grupo13.exceptions.InvalidTransferException;
import com.homebanking.grupo13.exceptions.TransferNotFoundException;
import com.homebanking.grupo13.mappers.TransferMapper;
import com.homebanking.grupo13.repositories.AccountRepository;
import com.homebanking.grupo13.repositories.TransferRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransferRepository transferRepository;

    public List<TransferDTO> getTransfers() {
        return transferRepository
                .findAll()
                .stream()
                .map(TransferMapper::transferToDto)
                .collect(Collectors.toList());
    }

    public TransferDTO getTransferById(Long id) {
        Transfer transfer = transferRepository.findById(id)
                .orElseThrow(() ->new TransferNotFoundException("Transferencia no encontrada"));

        return TransferMapper.transferToDto(transfer);
    }

    @Transactional(rollbackOn = Exception.class)
    public TransferDTO createTransfer(TransferDTO dto) {
        // Verificar que existen ambas cuentas
        Account accountSource = accountRepository.findById(dto.getAccountSourceId())
                .orElseThrow(() -> new AccountNotFoundException("Cuenta origen no encontrado"));
        Account accountDestine = accountRepository.findById(dto.getAccountDestineId())
                .orElseThrow(() -> new AccountNotFoundException("Cuenta destino no encontrado"));

        // Chequear que la cuenta origen tenga fondos suficiente
        if (accountSource.getAmount().compareTo(dto.getAmount()) < 0) {
            throw new InvalidTransferException("Fondos insuficientes");
        }

        // Operacion arimetica entre cuentas
        accountSource.setAmount(accountSource.getAmount().subtract(dto.getAmount()));
        accountDestine.setAmount(accountDestine.getAmount().add(dto.getAmount()));

        // Guardar en DB
        accountRepository.save(accountDestine);
        accountRepository.save(accountSource);

        // Crear la transferencia y guardar
        Transfer transfer = new Transfer();
        transfer.setAccountSourceId(accountSource.getId());
        transfer.setAccountDestineId(accountDestine.getId());
        transfer.setAmount(dto.getAmount());
        Transfer savedTransfer = transferRepository.save(transfer);

        return TransferMapper.transferToDto(savedTransfer);
    }
}

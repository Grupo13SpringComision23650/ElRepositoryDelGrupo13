package com.homebanking.grupo13.services;


import com.homebanking.grupo13.entities.Account;
import com.homebanking.grupo13.entities.Transfer;
import com.homebanking.grupo13.entities.dtos.TransferDto;
import com.homebanking.grupo13.exceptions.RecordNotFoundException;
import com.homebanking.grupo13.mappers.TransferMapper;
import com.homebanking.grupo13.repositories.IAccountRepository;
import com.homebanking.grupo13.repositories.ITransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import com.homebanking.grupo13.exceptions.InvalidStatusException;
@Service
public class TransferService {
    @Autowired
    private IAccountRepository accountRepository;
    @Autowired
    private ITransferRepository transferRepository;


    public List<TransferDto> getTransfers() {
        return transferRepository
                .findAll()
                .stream()
                .map(TransferMapper::transferToDto)
                .collect(Collectors.toList());
    }

    public TransferDto getTransferById(Long id) {
        Transfer transfer = transferRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Transferencia no encontrada id="+id));

        return TransferMapper.transferToDto(transfer);
    }

    //@Transactional(rollbackOn = Exception.class)
    public TransferDto createTransfer(TransferDto dto) {
        // Verificar que existen ambas cuentas
        Account accountSource = accountRepository.findById(dto.getAccountSourceId())
                .orElseThrow(() -> new RecordNotFoundException("Cuenta no encontrada id="+dto.getAccountSourceId()));

        Account accountDestine = accountRepository.findById(dto.getAccountDestineId())
                .orElseThrow(() -> new RecordNotFoundException("Cuenta no encontrada id="+dto.getAccountDestineId()));

        // Chequear que la cuenta origen tenga fondos suficiente
        if (accountSource.getAmount().compareTo(dto.getAmount()) < 0) {
            throw new InvalidStatusException("Fondos insuficientes");
        }
        if (!accountSource.getEnabled() || !accountDestine.getEnabled()) {
            throw new InvalidStatusException("Cuenta desabilitada");
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

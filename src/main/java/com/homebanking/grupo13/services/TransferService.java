package com.homebanking.grupo13.services;


import com.homebanking.grupo13.entities.Account;
import com.homebanking.grupo13.entities.Transfer;
import com.homebanking.grupo13.entities.dtos.TransferDto;
import com.homebanking.grupo13.exceptions.AccountNotFoundException;
import com.homebanking.grupo13.exceptions.InvalidTransferException;
import com.homebanking.grupo13.exceptions.TransferNotFoundException;
import com.homebanking.grupo13.mappers.TransferMapper;
import com.homebanking.grupo13.repositories.IAccountRepository;
import com.homebanking.grupo13.repositories.ITransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferService {
    @Autowired
    private IAccountRepository IAccountRepository;
    @Autowired
    private ITransferRepository ITransferRepository;


    public List<TransferDto> getTransfers() {
        return ITransferRepository
                .findAll()
                .stream()
                .map(TransferMapper::transferToDto)
                .collect(Collectors.toList());
    }

    public TransferDto getTransferById(Long id) {
        Transfer transfer = ITransferRepository.findById(id)
                .orElseThrow(() -> new TransferNotFoundException());

        return TransferMapper.transferToDto(transfer);
    }

    //@Transactional(rollbackOn = Exception.class)
    public TransferDto createTransfer(TransferDto dto) {
        // Verificar que existen ambas cuentas
        Account accountSource = IAccountRepository.findById(dto.getAccountSourceId())
                .orElseThrow(() -> new AccountNotFoundException());
        Account accountDestine = IAccountRepository.findById(dto.getAccountDestineId())
                .orElseThrow(() -> new AccountNotFoundException());
        // Chequear que la cuenta origen tenga fondos suficiente
        if (accountSource.getAmount().compareTo(dto.getAmount()) < 0) {
            throw new InvalidTransferException("Fondos insuficientes");
        }
        if (!accountSource.getEnabled() || !accountDestine.getEnabled()) {
            throw new InvalidTransferException("Cuenta desabilitada");
        }
        // Operacion arimetica entre cuentas
        accountSource.setAmount(accountSource.getAmount().subtract(dto.getAmount()));
        accountDestine.setAmount(accountDestine.getAmount().add(dto.getAmount()));

        // Guardar en DB
        IAccountRepository.save(accountDestine);
        IAccountRepository.save(accountSource);

        // Crear la transferencia y guardar
        Transfer transfer = new Transfer();
        transfer.setAccountSourceId(accountSource.getId());
        transfer.setAccountDestineId(accountDestine.getId());
        transfer.setAmount(dto.getAmount());
        Transfer savedTransfer = ITransferRepository.save(transfer);

        return TransferMapper.transferToDto(savedTransfer);
    }
}

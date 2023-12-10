package com.homebanking.grupo13.mappers;


import com.homebanking.grupo13.entities.Transfer;
import com.homebanking.grupo13.entities.dtos.TransferDto;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;


@UtilityClass
public class TransferMapper {

    public static Transfer dtoToTransfer(TransferDto dto) {
        Transfer transfer = new Transfer();
        transfer.setId(dto.getId());
        transfer.setAmount(dto.getAmount());
        transfer.setAccountSourceId(dto.getAccountSourceId());
        transfer.setAccountDestineId(dto.getAccountDestineId());
        transfer.setCreated_at(LocalDateTime.now());
        transfer.setUpdated_at(LocalDateTime.now());


        return transfer;
    }

    public static TransferDto transferToDto(Transfer transfer) {
        TransferDto dto = new TransferDto();
        dto.setId(transfer.getId());
        dto.setAmount(transfer.getAmount());
        dto.setAccountSourceId(transfer.getAccountSourceId());
        dto.setAccountDestineId(transfer.getAccountDestineId());
        dto.setCreated_at(LocalDateTime.now());
        dto.setUpdated_at(LocalDateTime.now());

        return dto;
    }
}

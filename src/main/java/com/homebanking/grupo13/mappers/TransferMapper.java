package com.homebanking.grupo13.mappers;


import com.homebanking.grupo13.entities.Transfer;
import com.homebanking.grupo13.entities.dtos.TransferDTO;
import lombok.experimental.UtilityClass;


@UtilityClass
public class TransferMapper {

    public static Transfer dtoToTransfer(TransferDTO dto) {
        Transfer transfer = new Transfer();
        transfer.setId(dto.getId());
        transfer.setAmount(dto.getAmount());
        transfer.setAccountSourceId(dto.getAccountSourceId());
        transfer.setAccountDestineId(dto.getAccountDestineId());

        return transfer;
    }

    public static TransferDTO transferToDto(Transfer transfer) {
        TransferDTO dto = new TransferDTO();
        dto.setId(transfer.getId());
        dto.setAmount(transfer.getAmount());
        dto.setAccountSourceId(transfer.getAccountSourceId());
        dto.setAccountDestineId(transfer.getAccountDestineId());

        return dto;
    }
}
